package top.moma.zoffy.config.advice;

import java.util.Objects;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.moma.m64.common.helper.json.JsonHelper;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.zoffy.annotation.IgnoreResponseAdvice;
import top.moma.zoffy.annotation.ZoffyResponse;
import top.moma.zoffy.framework.trace.TraceWorker;
import top.moma.zoffy.support.reaponse.Response;
import top.moma.zoffy.support.reaponse.SuccessResponse;

/**
 * ZoffyResponseAdvice
 *
 * <p>RestController返回时，统一封装Response
 *
 * @version 1.0
 * @author Created by ivan at 16:35.
 */
@RestControllerAdvice
@Order(1)
public class ZoffyResponseAdvice implements ResponseBodyAdvice<Object> {

  /**
   * supports
   *
   * <p>方法携带IgnoreResponseAdvice，不处理
   *
   * <p>类或方法携带ZoffyResponse，处理
   *
   * <p>其他，不处理
   *
   * @param returnType returnType
   * @param converterType converterType
   * @return boolean
   * @author Created by ivan
   * @since 2023/3/22 16:35
   */
  @Override
  public boolean supports(
      MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
    if (Objects.requireNonNull(returnType.getMethod())
        .isAnnotationPresent(IgnoreResponseAdvice.class)) {
      return false;
    }
    return returnType.getContainingClass().isAnnotationPresent(ZoffyResponse.class)
        || Objects.requireNonNull(returnType.getMethod()).isAnnotationPresent(ZoffyResponse.class);
  }

  /**
   * beforeBodyWrite
   *
   * <p>Header添加TraceID
   *
   * <p>返回对象已经是Response及其子类的，直接返回
   *
   * <p>String类型，将String作为data部分，拼装Json格式的响应String
   *
   * <p>其他作为data部分，构建SuccessResponse对象，返回
   *
   * @param body body
   * @param returnType returnType
   * @param selectedContentType selectedContentType
   * @param selectedConverterType selectedConverterType
   * @param request request
   * @param response response
   * @return java.lang.Object
   * @author Created by ivan
   * @since 2023/3/22 16:37
   */
  @Override
  public Object beforeBodyWrite(
      Object body,
      @NonNull MethodParameter returnType,
      @NonNull MediaType selectedContentType,
      @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
      @NonNull ServerHttpRequest request,
      @NonNull ServerHttpResponse response) {
    response.getHeaders().add(TraceWorker.TRACE_ID, TraceWorker.getTraceId());
    if (ObjectHelper.isNotEmpty(body) && Response.class.isAssignableFrom(body.getClass())) {
      return body;
    }
    if (body instanceof String) {
      response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
      return JsonHelper.toJson(SuccessResponse.success(body));
    }
    return SuccessResponse.success(body);
  }
}
