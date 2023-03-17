package top.moma.zoffy.report.logic.constants;

/**
 * AccountCodeConstants
 *
 * <p>巴基斯坦凭证科目计算模版常量
 *
 * @version 1.0
 * @author Created by ivan at 15:07.
 */
public class AccountCodeConstants {
  /** 模版 - 数据来源 - Loan Data */
  public static final String SOURCE_TYPE_LOAN_DATA = "loandata";
  /** 模版 - 数据来源 - Cash in Flow */
  public static final String SOURCE_TYPE_CASH_IN = "cashin";
  /** 模版 - 数据来源 - Cash out Flow */
  public static final String SOURCE_TYPE_CASH_OUT = "cashout";
  /** 模版 - 数据来源 - AP and AR */
  public static final String SOURCE_TYPE_AP_AR = "apar";
  /** 模版 - 数据来源 - Entries */
  public static final String SOURCE_TYPE_ENTRY = "entry";
  /** 模版 - 数据来源 - Manual */
  public static final String SOURCE_TYPE_MANUAL = "manual";

  /** 模版 - 计算顺序 - 表内计算序号 */
  public static final int CAL_LEVEL_INNER = 99;

  /** 模版 - 手动科目计算关联类型 - 重新计算 */
  public static final String MANUAL_TYPE_EFFECT = "0";
  /** 模版 - 手动科目计算关联类型 - 借贷同步 */
  public static final String MANUAL_TYPE_MIRROR = "1";

  /** 模版 - 借贷方向 - 借方 DR */
  public static final int TYPE_DR = 0;
  /** 模版 - 借贷方向 - 贷方 CR */
  public static final int TYPE_CR = 1;

  private AccountCodeConstants() {
    throw new IllegalStateException("Constants class");
  }
}
