package top.moma.zoffy.framework.log.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * RequestLogDTO
 *
 * <p>请求日志对象
 *
 * @version 1.0
 * @author Created by ivan at 17:32.
 */
@Data
@Builder
public class RequestLogDTO {
  /** 请求id，同trace id */
  private String requestId;
  /** 请求路径参数，排序 */
  private Map<String, String[]> parameterMap;
  /** 请求内容对象 */
  private Object requestBody;
  /** 请求路径 */
  private String url;
  /** 请求方式 */
  private String method;
  /** 响应对象 */
  private Object result;
  /** 执行耗时 ns */
  private String runTime;
  /** 最终响应方法 */
  private String handler;
  /** 请求ip */
  private String ip;
}
