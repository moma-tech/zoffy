package top.moma.zoffy.framework.log;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import top.moma.m64.common.helper.json.JsonHelper;
import top.moma.zoffy.common.constants.ApiConstants;
import top.moma.zoffy.framework.log.dto.RequestLogDTO;
import top.moma.zoffy.support.request.RequestHelper;

/**
 * HttpCallLogPrinter
 *
 * <p>请求日志统一输出
 *
 * @version 1.0
 * @author Created by ivan at 17:53.
 */
@Slf4j
public class HttpCallLogPrinter {

  /**
   * print
   *
   * <p>数据拼装
   *
   * @param request request
   * @param result result
   * @param handler handler
   * @author Created by ivan
   * @since 2023/3/22 17:53
   */
  public static void print(HttpServletRequest request, Object result, String handler) {
    print(
        (String) request.getAttribute(ApiConstants.REQUEST_ID),
        RequestHelper.getParameterMap(request),
        RequestHelper.getRequestBody(request),
        (String) request.getAttribute(ApiConstants.REQUEST_URL),
        handler,
        (String) request.getAttribute(ApiConstants.REQUEST_METHOD),
        result,
        (Long) request.getAttribute(ApiConstants.REQUEST_START_TIME),
        RequestHelper.getIpAddress(request));
  }

  /**
   * print
   *
   * <p>Json 格式
   *
   * @param requestId requestId
   * @param parameterMap parameterMap
   * @param requestBody requestBody
   * @param url url
   * @param handler handler
   * @param method method
   * @param result result
   * @param runTime runTime
   * @param ip ip
   * @author Created by ivan
   * @since 2023/3/22 17:54
   */
  public static void print(
      String requestId,
      Map<String, String[]> parameterMap,
      Object requestBody,
      String url,
      String handler,
      String method,
      Object result,
      Long runTime,
      String ip) {
    log.info(
        JsonHelper.toJson(
            RequestLogDTO.builder()
                .requestId(requestId)
                .parameterMap(parameterMap)
                .requestBody(requestBody)
                .url(url)
                .method(method)
                .result(result)
                .runTime((null != runTime ? System.nanoTime() - runTime : 0) + " ns")
                .handler(handler)
                .ip(ip)
                .build()));
  }

  private HttpCallLogPrinter() {
    throw new IllegalStateException("Constant class");
  }
}
