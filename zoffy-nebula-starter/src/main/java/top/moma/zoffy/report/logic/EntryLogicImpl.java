package top.moma.zoffy.report.logic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.moma.m64.core.exceptions.M64Exception;
import top.moma.m64.core.helper.cache.WeakMapCache;
import top.moma.zoffy.report.entity.PkGlEntries;
import top.moma.zoffy.report.entity.PkGlEntriesAccountCode;
import top.moma.zoffy.report.logic.constants.AccountCodeConstants;
import top.moma.zoffy.report.logic.constants.CalFunctionEnumFactory;
import top.moma.zoffy.report.logic.constants.EntryBizConstants;
import top.moma.zoffy.report.service.PkGlEntriesAccountCodeService;
import top.moma.zoffy.report.service.PkGlEntriesService;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EntryLogicImpl {
  String version = "202303";

  @Autowired PkGlEntriesService entriesService;

  @Autowired PkGlEntriesAccountCodeService accountCodeService;

  public void generatePeriodEntry(String period, String opUser) {
    WeakMapCache<String, BigDecimal> weakMapCache = new WeakMapCache<>();
    List<PkGlEntries> existEntries =
        entriesService.list(
            new LambdaQueryWrapper<PkGlEntries>().eq(PkGlEntries::getEntryPeriod, period));
    List<PkGlEntriesAccountCode> accountCodeList = getAccountCode(version);
    List<PkGlEntriesAccountCode> delayList = new ArrayList<>(12);
    List<PkGlEntries> entriesList =
        accountCodeList.stream()
            .filter(
                accountCode -> {
                  if (accountCode.getCalLevel().equals(AccountCodeConstants.CAL_LEVEL_INNER)) {
                    delayList.add(accountCode);
                    return false;
                  }
                  return true;
                })
            .map(
                accountCode -> {
                  PkGlEntries entry;
                  // 处理手动输入
                  if (accountCode.getSource().equals(AccountCodeConstants.SOURCE_TYPE_MANUAL)) {
                    entry =
                        existEntries.stream()
                            .filter(
                                entries1 ->
                                    entries1.getAccountCodeId().equals(accountCode.getAcId()))
                            .findFirst()
                            .orElse(calculate(weakMapCache, accountCode, period));
                  } else {
                    entry = calculate(weakMapCache, accountCode, period);
                  }
                  entry.setEnable(1);
                  if (StringUtils.hasText(opUser)) {
                    entry.setUpdateUser(opUser);
                  }
                  return entry;
                })
            .collect(Collectors.toList());
    entriesService.removeByIds(existEntries);
    entriesService.saveBatch(entriesList);
    for (PkGlEntriesAccountCode accountCode : delayList) {
      PkGlEntries entries = calculate(weakMapCache, accountCode, period);
      entries.setEnable(1);
      if (StringUtils.hasText(opUser)) {
        entries.setUpdateUser(opUser);
      }
      entriesService.save(entries);
    }
    weakMapCache.empty();
  }

  public void recalculateEntries(String startPeriod, String opUser) {
    List<String> periods = periodRange(startPeriod);
    periods.parallelStream().forEach(p -> generatePeriodEntry(p, opUser));
  }

  @Transactional(rollbackFor = {RuntimeException.class, M64Exception.class})
  public boolean updateManualEntry(String period, String entryId, BigDecimal value, String opUser) {
    boolean result = false;
    PkGlEntries entry = entriesService.getById(entryId);
    PkGlEntriesAccountCode accountCode = accountCodeService.getById(entry.getAccountCodeId());
    if (!accountCode.getSource().equals(AccountCodeConstants.SOURCE_TYPE_MANUAL)) {
      throw new M64Exception("Not a Manual Account Type");
    }
    String params = accountCode.getCalParams();
    calculateBalance(entry, accountCode.getDrcr(), value);
    if (StringUtils.hasText(opUser)) {
      entry.setUpdateUser(opUser);
    }
    // 查询关联
    String[] relations = params.substring(1).split(",");
    List<PkGlEntriesAccountCode> accountCodeList =
        accountCodeService.list(
            new LambdaQueryWrapper<PkGlEntriesAccountCode>()
                .eq(PkGlEntriesAccountCode::getVersion, version)
                .in(PkGlEntriesAccountCode::getCode, Arrays.asList(relations)));
    // 更新entry
    try {
      result = entriesService.updateById(entry);
      // 关联计算，借贷同步
      if (params.startsWith(AccountCodeConstants.MANUAL_TYPE_MIRROR)) {
        accountCodeList.parallelStream()
            .forEach(
                ac -> {
                  PkGlEntries relate = new PkGlEntries();
                  if (StringUtils.hasText(opUser)) {
                    relate.setUpdateUser(opUser);
                  }
                  calculateBalance(relate, ac.getDrcr(), value);
                  entriesService.update(
                      relate,
                      new LambdaUpdateWrapper<PkGlEntries>()
                          .eq(PkGlEntries::getEntryPeriod, period)
                          .eq(PkGlEntries::getAccountCodeId, ac.getAcId()));
                });
      }
      // 关联计算，重新计算
      else if (params.startsWith(AccountCodeConstants.MANUAL_TYPE_EFFECT)) {
        WeakMapCache<String, BigDecimal> weakMapCache = new WeakMapCache<>();
        accountCodeList.parallelStream()
            .forEach(
                ac -> {
                  PkGlEntries affected = calculate(weakMapCache, ac, period);
                  if (StringUtils.hasText(opUser)) {
                    affected.setUpdateUser(opUser);
                  }
                  entriesService.update(
                      affected,
                      new LambdaUpdateWrapper<PkGlEntries>()
                          .eq(PkGlEntries::getEntryPeriod, period)
                          .eq(PkGlEntries::getAccountCodeId, ac.getAcId()));
                });
        weakMapCache.empty();
      }
    } catch (Exception e) {
      throw new M64Exception("System Error");
    }
    return result;
  }

  /**
   * 获取凭证科目计算模版 getAccountCode
   *
   * @param version version
   * @return java.util.List<com.opay.fund.gl.report.provider.entity.pk.entry.PkGlEntriesAccountCode>
   * @author Created by ivan
   * @since 2023/3/10 20:20
   */
  private List<PkGlEntriesAccountCode> getAccountCode(String version) {
    // TODO May Cache template,"gl-report-pk-ac-" + version
    return accountCodeService.list(
        new LambdaQueryWrapper<PkGlEntriesAccountCode>()
            .eq(PkGlEntriesAccountCode::getVersion, version)
            .orderByAsc(
                Arrays.asList(
                    PkGlEntriesAccountCode::getCalLevel, PkGlEntriesAccountCode::getCode)));
  }

  /**
   * 单个科目基于模版计算 calculate
   *
   * @param weakMapCache 计算过程缓存
   * @param accountCode 科目模版
   * @param period 计算周期
   * @return com.opay.fund.gl.report.provider.entity.pk.entry.PkGlEntries
   * @author Created by ivan
   * @since 2023/3/10 20:12
   */
  private PkGlEntries calculate(
      WeakMapCache<String, BigDecimal> weakMapCache,
      PkGlEntriesAccountCode accountCode,
      String period) {
    String key =
        period
            + "_"
            + accountCode.getSource()
            + "_"
            + accountCode.getCalFunction()
            + "_"
            + accountCode.getCalParams();
    PkGlEntries entries = new PkGlEntries();
    entries.setEntryPeriod(period);
    entries.setAccountCodeId(accountCode.getAcId());
    entries.setSort(accountCode.getCode());
    entries.setEnable(1);
    BigDecimal value = weakMapCache.get(key);
    if (Objects.isNull(value)) {
      value =
          CalFunctionEnumFactory.valueOf(accountCode.getCalFunction())
              .calculate(accountCode.getSource(), period, accountCode.getCalParams().split(","));
      weakMapCache.put(key, value);
    }
    calculateBalance(entries, accountCode.getDrcr(), value);
    return entries;
  }

  /**
   * dr,cr,balance计算赋值 calculateBalance
   *
   * @param entries 记录
   * @param drcrType 借贷方向
   * @param value 金额
   * @author Created by ivan
   * @since 2023/3/13 16:00
   */
  private void calculateBalance(PkGlEntries entries, Integer drcrType, BigDecimal value) {
    DecimalFormat df = new DecimalFormat("####");
    if (drcrType.equals(AccountCodeConstants.TYPE_DR)) {
      entries.setDr(df.format(value));
      entries.setCr(BigDecimal.ZERO.toPlainString());
      entries.setBalance(value.toPlainString());
    } else {
      entries.setCr(value.toPlainString());
      entries.setDr(BigDecimal.ZERO.toPlainString());
      entries.setBalance(BigDecimal.ZERO.subtract(value).toPlainString());
    }
  }

  /**
   * 获取从开始月份到上个月的所有月份 periodRange
   *
   * @param startPeriod 开始月份，yyyy-MM
   * @return java.util.List<java.lang.String>
   * @author Created by ivan
   * @since 2023/3/10 20:43
   */
  private List<String> periodRange(String startPeriod) {
    List<String> periods = new ArrayList<>();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    SimpleDateFormat format = new SimpleDateFormat(EntryBizConstants.DEFAULT_PERIOD_FORMAT);
    Date startDate;
    try {
      startDate = format.parse(startPeriod);
    } catch (ParseException e) {
      startDate = new Date();
    }
    calendar.add(Calendar.MONTH, -1);
    Date endDate = calendar.getTime();
    calendar.setTime(startDate);
    while (calendar.getTime().before(endDate)) {
      periods.add(format.format(calendar.getTime()));
      calendar.add(Calendar.MONTH, 1);
    }
    return periods;
  }
}
