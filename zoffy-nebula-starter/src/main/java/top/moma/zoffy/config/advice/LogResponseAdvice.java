package top.moma.zoffy.config.advice;

import java.lang.reflect.Method;
import java.util.Objects;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.moma.m64.core.constants.StringConstants;
import top.moma.zoffy.framework.log.HttpCallLogPrinter;
import top.moma.zoffy.support.request.RequestHelper;

/**
 * LogResponseAdvice
 *
 * <p>RestController 返回时，统一打印请求+响应日志
 *
 * @version 1.0
 * @author Created by ivan at 16:32.
 */
@RestControllerAdvice
@Order(99)
public class LogResponseAdvice implements ResponseBodyAdvice<Object> {

  /**
   * supports 所有RestController
   *
   * @param returnType returnType
   * @param converterType converterType
   * @return boolean
   * @author Created by ivan
   * @since 2023/3/22 16:34
   */
  @Override
  public boolean supports(
      @NonNull MethodParameter returnType,
      @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  /**
   * beforeBodyWrite 调用日志打印方法
   *
   * @param body body
   * @param returnType returnType
   * @param selectedContentType selectedContentType
   * @param selectedConverterType selectedConverterType
   * @param request request
   * @param response response
   * @return java.lang.Object
   * @author Created by ivan
   * @since 2023/3/22 16:34
   */
  @Override
  public Object beforeBodyWrite(
      Object body,
      @NonNull MethodParameter returnType,
      @NonNull MediaType selectedContentType,
      @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
      @NonNull ServerHttpRequest request,
      @NonNull ServerHttpResponse response) {
    String handler = StringConstants.EMPTY;
    Method method = returnType.getMethod();
    if (Objects.nonNull(method)) {
      handler = method.getDeclaringClass().getName() + StringConstants.DOT + method.getName();
    }
    HttpCallLogPrinter.print(RequestHelper.getRequest(), body, handler);
    return body;
  }
}
