package top.moma.zoffy.report.logic.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import top.moma.zoffy.report.entity.PkGlEntries;
import top.moma.zoffy.report.entity.PkGlEntriesAccountCode;
import top.moma.zoffy.report.logic.constants.EntryBizConstants;
import top.moma.zoffy.report.service.PkGlEntriesAccountCodeService;
import top.moma.zoffy.report.service.PkGlEntriesService;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * EntrySourceProvider
 *
 * <p>凭证数据计算
 *
 * @version 1.0
 * @author Created by ivan at 19:00.
 */
@Component(EntryBizConstants.SOURCE_PROVIDER_ENTRY)
public class EntrySourceProvider implements SourceProvider {
  @Value("${gl.report.pk.entry.ac.version:202303}")
  String version;

  final PkGlEntriesService entriesService;
  final PkGlEntriesAccountCodeService accountCodeService;

  public EntrySourceProvider(
      PkGlEntriesService entriesService, PkGlEntriesAccountCodeService accountCodeService) {
    this.entriesService = entriesService;
    this.accountCodeService = accountCodeService;
  }

  @Override
  public BigDecimal accumulateByPeriod(String period, String... params) {
    return BigDecimal.ZERO;
  }

  @Override
  public BigDecimal accumulateByPlatform(String period, String... params) {
    return BigDecimal.ZERO;
  }

  @Override
  public BigDecimal sum(String period, String... args) {
    @SuppressWarnings("unchecked")
    List<String> params = (List<String>) CollectionUtils.arrayToList(args);
    List<PkGlEntriesAccountCode> accountCodeList = queryAccountCode(params);
    return calculate(accountCodeList, period, BigDecimal::add);
  }

  @Override
  public BigDecimal minus(String period, String... args) {
    @SuppressWarnings("unchecked")
    List<String> params = (List<String>) CollectionUtils.arrayToList(args);
    List<PkGlEntriesAccountCode> accountCodeList = queryAccountCode(params);
    return calculate(accountCodeList, period, BigDecimal::subtract);
  }

  @Override
  public BigDecimal sumMinus(String period, String... args) {
    int split = Integer.parseInt(args[0]);
    @SuppressWarnings("unchecked")
    List<String> params = (List<String>) CollectionUtils.arrayToList(args).subList(1, args.length);
    List<PkGlEntriesAccountCode> accountCodeList = queryAccountCode(params);
    if (CollectionUtils.isEmpty(accountCodeList) || split >= accountCodeList.size()) {
      return BigDecimal.ZERO;
    } else {
      BigDecimal firstSet = calculate(accountCodeList.subList(0, split), period, BigDecimal::add);
      BigDecimal secondSet =
          calculate(
              accountCodeList.subList(split, accountCodeList.size()), period, BigDecimal::add);
      return firstSet.subtract(secondSet);
    }
  }

  /**
   * description queryAccountCode
   *
   * @param params params
   * @return java.util.List<com.opay.fund.gl.report.provider.entity.pk.entry.PkGlEntriesAccountCode>
   * @author Created by ivan
   * @since 2023/3/10 19:01
   */
  private List<PkGlEntriesAccountCode> queryAccountCode(List<String> params) {
    return accountCodeService.list(
        new LambdaQueryWrapper<PkGlEntriesAccountCode>()
            .eq(PkGlEntriesAccountCode::getVersion, version)
            .in(PkGlEntriesAccountCode::getCode, params)
            .last(" order by field(code," + String.join(",", params) + ")"));
  }

  /**
   * 凭证科目计算 calculate
   *
   * @param range 科目编号
   * @param period 凭证周期
   * @param biFunction 计算方式
   * @return java.math.BigDecimal 计算结果
   * @author Created by ivan
   * @since 2023/3/10 19:01
   */
  private BigDecimal calculate(
      List<PkGlEntriesAccountCode> range, String period, BinaryOperator<BigDecimal> biFunction) {
    if (CollectionUtils.isEmpty(range)) {
      return BigDecimal.ZERO;
    }
    List<PkGlEntries> entrySet =
        entriesService.list(
            new LambdaQueryWrapper<PkGlEntries>()
                .in(
                    PkGlEntries::getAccountCodeId,
                    range.stream()
                        .map(PkGlEntriesAccountCode::getAcId)
                        .collect(Collectors.toList()))
                .eq(PkGlEntries::getEntryPeriod, period));
    if (CollectionUtils.isEmpty(entrySet)) {
      return BigDecimal.ZERO;
    } else {
      BigDecimal initValue = entryCalculation(entrySet.get(0));
      return entrySet.stream()
          .skip(1)
          .map(EntrySourceProvider::entryCalculation)
          .reduce(initValue, biFunction);
    }
  }

  /**
   * 科目计算辅助 entryCalculation
   *
   * @param entries 科目数据
   * @return java.math.BigDecimal 科目数值
   * @author Created by ivan
   * @since 2023/3/10 19:02
   */
  private static BigDecimal entryCalculation(PkGlEntries entries) {
    BigDecimal dr = new BigDecimal(entries.getDr());
    return dr.add(new BigDecimal(entries.getCr()));
  }
}
