package top.moma.zoffy.framework.trace;

/**
 * TraceContext
 *
 * @version 1.0
 * @author Created by ivan at 16:50.
 */
public class TraceContext {
  private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

  public static void setTraceId(String traceId) {
    THREAD_LOCAL.set(traceId);
  }

  public static String getTraceId() {
    return THREAD_LOCAL.get();
  }

  public static void clear() {
    THREAD_LOCAL.remove();
  }

  private TraceContext() {
    throw new UnsupportedOperationException("Not Support");
  }
}
