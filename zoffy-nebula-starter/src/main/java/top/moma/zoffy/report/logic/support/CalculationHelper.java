package top.moma.zoffy.report.logic.support;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.moma.m64.core.exceptions.M64Exception;
import top.moma.zoffy.report.logic.constants.EntryBizConstants;
import top.moma.zoffy.report.logic.constants.EntryExceptionEnum;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CalculationHelper
 *
 * <p>计算辅助
 *
 * @version 1.0
 * @author Created by ivan at 15:40.
 */
public class CalculationHelper {
  /** 年第一个月 */
  public static final String JANUARY = "01";
  /** 年占位符 */
  public static final String YEAR_PLACEHOLDER = "yyyy";
  /** 月占位符 */
  public static final String MONTH_PLACEHOLDER = "MM";

  /**
   * 获取年第一个月 startPeriod
   *
   * @param period period 计算周期
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/3/10 15:40
   */
  public static String startPeriod(String period) {
    if (period.length() > 4) {
      return period.substring(0, period.length() - 2) + JANUARY;
    } else {
      throw new M64Exception(
          EntryExceptionEnum.PERIOD_INVALID.getCode(), EntryExceptionEnum.PERIOD_INVALID.getMsg());
    }
  }

  /**
   * 拼装求和SELECT sumSelect
   *
   * @param calParams 求和列
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/3/10 16:06
   */
  private static String sumSelect(List<String> calParams) {
    String select = "1";
    if (!CollectionUtils.isEmpty(calParams)) {
      select =
          "SUM(CAST("
              + String.join("+", calParams)
              + " AS UNSIGNED INTEGER)) as "
              + EntryBizConstants.CALCULATION_COMMON_SUM;
    }
    return select;
  }

  /**
   * 拼装求和SQL sumQueryWrapper
   *
   * @param platform 渠道，查询时转小写，需数据库编码支持
   * @param period 计算结束周期
   * @param calParams 求和列
   * @param platformColCons 渠道字段名称
   * @param periodColCons 周期字段名称
   * @return com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<T> 查询Wrapper
   * @author Created by ivan
   * @since 2023/3/10 16:06
   */
  public static <T> QueryWrapper<T> sumQueryWrapper(
      String platform,
      String period,
      List<String> calParams,
      String platformColCons,
      String periodColCons) {
    if (StringUtils.hasText(platform)) {
      return new QueryWrapper<T>()
          .select(sumSelect(calParams))
          .eq(platformColCons, platform.toLowerCase(Locale.ROOT))
          .between(periodColCons, startPeriod(period), period);
    } else {
      return new QueryWrapper<T>()
          .select(sumSelect(calParams))
          .between(periodColCons, startPeriod(period), period);
    }
  }

  /**
   * 计算周期格式化 periodFormatter
   *
   * @param format 数据源格式
   * @param period 传入格式
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/3/10 16:45
   */
  public static String periodFormatter(String format, String period) {
    String formattedPeriod = format;
    Pattern p = Pattern.compile("\\D");
    Matcher matcher = p.matcher(period);
    period = matcher.replaceAll("").trim();
    formattedPeriod = formattedPeriod.replace(YEAR_PLACEHOLDER, period.substring(0, 4));
    formattedPeriod = formattedPeriod.replace(MONTH_PLACEHOLDER, period.substring(4, 6));
    return formattedPeriod;
  }

  private CalculationHelper() {
    throw new IllegalStateException("Utility class");
  }
}
