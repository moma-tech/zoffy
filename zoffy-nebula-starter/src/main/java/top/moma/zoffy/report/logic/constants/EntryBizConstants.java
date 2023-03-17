package top.moma.zoffy.report.logic.constants;

/**
 * EntryBizConstants
 *
 * <p>凭证业务相关常量
 *
 * @version 1.0
 * @author Created by ivan at 14:58.
 */
public class EntryBizConstants {
  /** 计算结果 - 统一属性 */
  public static final String CALCULATION_COMMON_SUM = "sumAll";

  /** 计算代理 - Loan Data */
  public static final String SOURCE_PROVIDER_LOAN_DATA = "loadDataSourceProvider";
  /** 计算代理 - Cash in Flow */
  public static final String SOURCE_PROVIDER_CASH_IN = "cashInSourceProvider";
  /** 计算代理 - Cash out Flow */
  public static final String SOURCE_PROVIDER_CASH_OUT = "cashOutSourceProvider";
  /** 计算代理 - AP and AR */
  public static final String SOURCE_PROVIDER_AP_AR = "aparSourceProvider";
  /** 计算代理 - Entries */
  public static final String SOURCE_PROVIDER_ENTRY = "entrySourceProvider";
  /** 计算代理 - Manual */
  public static final String SOURCE_PROVIDER_MANUAL = "manual";

  /** 默认周期格式类型 */
  public static final String DEFAULT_PERIOD_FORMAT = "yyyy-MM";

  private EntryBizConstants() {
    throw new IllegalStateException("Constants class");
  }
}
