package top.moma.zoffy.report.logic.constants;

/**
 * EntryExceptionEnum
 *
 * <p>Entry 相关异常
 *
 * @version 1.0
 * @author Created by ivan at 16:05.
 */
public enum EntryExceptionEnum {
  PERIOD_INVALID("E10000", "Calculate Period is invalid."),
  ;

  private final String code;
  private final String msg;

  EntryExceptionEnum(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public String getCode() {
    return this.code;
  }

  public String getMsg() {
    return this.msg;
  }
}
