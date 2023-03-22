package top.moma.zoffy.common.enumerations;

import jakarta.servlet.http.HttpServletResponse;

/**
 * HttpResponseEnum
 *
 * <p>HTTP通用响应转换枚举
 *
 * <p>TODO 扩展i18n
 *
 * @version 1.0
 * @author Created by ivan at 16:31.
 */
public enum HttpResponseEnum {
  // 2xx Success
  /** 200 */
  OK(HttpServletResponse.SC_OK, "请求已成功"),
  /** 201 */
  CREATED(HttpServletResponse.SC_CREATED, "请求资源已创建"),
  /** 202 */
  ACCEPTED(HttpServletResponse.SC_ACCEPTED, "服务器已接受请求"),
  /** 203 */
  NON_AUTHORITATIVE_INFORMATION(
      HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION, "请求已处理，非授权信息"),
  /** 204 */
  NO_CONTENT(HttpServletResponse.SC_NO_CONTENT, "请求已处理，无返回"),
  /** 205 */
  RESET_CONTENT(HttpServletResponse.SC_RESET_CONTENT, "请求已处理，连接重置"),
  // 4xx Request Error Response
  /** 400 */
  BAD_REQUEST(HttpServletResponse.SC_BAD_REQUEST, "请求参数错误或不完整"),
  /** 401 */
  UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "请先进行授权认证"),
  /** 403 */
  FORBIDDEN(HttpServletResponse.SC_FORBIDDEN, "无权查看"),
  /** 404 */
  NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "未找到该资源"),
  /** 405 */
  METHOD_NOT_ALLOWED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "请求方式不支持"),
  /** 406 */
  NOT_ACCEPTABLE(HttpServletResponse.SC_NOT_ACCEPTABLE, "请求被拒绝"),
  /** 411 */
  LENGTH_REQUIRED(HttpServletResponse.SC_LENGTH_REQUIRED, "长度受限"),
  /** 415 */
  UNSUPPORTED_MEDIA_TYPE(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "不支持的媒体类型"),
  /** 416 */
  REQUESTED_RANGE_NOT_SATISFIABLE(
      HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, "不能满足请求的范围"),
  // 5xx Service Error Response
  /** 500 */
  INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "接口服务错误"),
  /** 503 */
  SERVICE_UNAVAILABLE(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "请求超时"),
  ;

  /** Code */
  private final int code;
  /** Message */
  private final String msg;

  HttpResponseEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int code() {
    return code;
  }

  public String msg() {
    return msg;
  }
}
