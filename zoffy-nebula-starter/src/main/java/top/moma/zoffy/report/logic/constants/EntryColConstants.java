package top.moma.zoffy.report.logic.constants;

/**
 * EntryColConstants
 *
 * <p>属性列常量
 *
 * @version 1.0
 * @author Created by ivan at 15:46.
 */
public class EntryColConstants {
  /** 第三方通道 - loan data */
  public static final String PLATFORM_COL_LOAN_DATA = "third_party_platform";
  /** 第三方通道 - cash in */
  public static final String PLATFORM_COL_CASH_IN = "third_party_platform";
  /** 第三方通道 - cash out */
  public static final String PLATFORM_COL_CASH_OUT = "third_party_platform";
  /** 第三方通道 - ap ar */
  public static final String PLATFORM_COL_AP_AR = "third_party_platform";

  /** 数据周期 - loan data */
  public static final String PERIOD_COL_LOAN_DATA = "data_month";
  /** 数据周期 - cash in */
  public static final String PERIOD_COL_CASH_IN = "paid_month";
  /** 数据周期 - cash out */
  public static final String PERIOD_COL_CASH_OUT = "loan_month";
  /** 数据周期 - ap ar */
  public static final String PERIOD_COL_AP_AR = "settle_period";

  /** 数据周期格式 - loan data */
  public static final String PERIOD_FORMAT_LOAN_DATA = "yyyy-MM";
  /** 数据周期格式 - cash in */
  public static final String PERIOD_FORMAT_CASH_IN = "yyyy-MM";
  /** 数据周期格式 - cash out */
  public static final String PERIOD_FORMAT_CASH_OUT = "yyyy-MM";
  /** 数据周期格式 - ap ar */
  public static final String PERIOD_FORMAT_AP_AR = "yyyy-MM";

  private EntryColConstants() {
    throw new IllegalStateException("Constants class");
  }
}
