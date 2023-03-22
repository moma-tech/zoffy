package top.moma.zoffy.common.constants;

/**
 * ApiConstants
 *
 * <p>Api/Controller 层自定义通用常量
 *
 * @version 1.0
 * @author Created by ivan at 16:29.
 */
public class ApiConstants {
  /* Request Ref. */
  /** Request Start Time */
  public static final String REQUEST_START_TIME = "startTime";
  /** Request Id */
  public static final String REQUEST_ID = "requestId";
  /** Request URL */
  public static final String REQUEST_URL = "url";
  /** Request Method */
  public static final String REQUEST_METHOD = "method";
  /** Request Mapping */
  public static final String REQUEST_MAPPING = "mapping";

  private ApiConstants() {
    throw new IllegalStateException("Constant class");
  }
}
