package top.moma.zoffy.framework.trace;

import java.util.UUID;
import org.springframework.util.ObjectUtils;

/**
 * TraceWorker
 *
 * <p>Trace 操作类
 *
 * @version 1.0
 * @author Created by ivan at 17:55.
 */
public class TraceWorker {
  /** MCD Key */
  public static final String TRACE_ID = "ZOFFY_TRACE_ID";

  /**
   * get TraceId
   *
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/2/3 10:06
   */
  public static String getTraceId() {
    if (ObjectUtils.isEmpty(TraceContext.getTraceId())) {
      String tranceId = UUID.randomUUID().toString();
      TraceContext.setTraceId(tranceId);
    }
    return TraceContext.getTraceId();
  }

  /**
   * set TraceId
   *
   * @param traceId traceId
   * @author Created by ivan
   * @since 2023/2/3 10:06
   */
  public static void setTraceId(String traceId) {
    TraceContext.setTraceId(traceId);
  }

  /**
   * clear
   *
   * @author Created by ivan
   * @since 2023/3/22 16:55
   */
  public static void clear() {
    TraceContext.clear();
  }

  private TraceWorker() {
    throw new IllegalStateException("Utility class");
  }
}
