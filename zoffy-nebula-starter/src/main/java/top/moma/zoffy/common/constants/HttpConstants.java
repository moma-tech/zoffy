package top.moma.zoffy.common.constants;

/**
 * HttpConstants
 *
 * <p>HTTP协议相关常量
 *
 * @version 1.0
 * @author Created by ivan at 16:29.
 */
public class HttpConstants {
  /** Min IP Length */
  public static final Integer IP_MIN_LENGTH = 15;
  /** Proxy Header Char */
  public static final String UNKNOWN = "unknown";
  /** Proxy Header Char */
  public static final String X_REAL_IP = "X-Real-IP";
  /** Proxy Header Char */
  public static final String X_FORWARDED_FOR = "X-Forwarded-For";
  /** Proxy Header Char */
  public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
  /** Proxy Header Char */
  public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

  private HttpConstants() {
    throw new IllegalStateException("Constants class");
  }
}
