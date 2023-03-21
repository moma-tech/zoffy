package top.moma.zoffy.config.advice;

import java.util.Objects;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.moma.m64.common.helper.json.JsonHelper;
import top.moma.zoffy.annotation.IgnoreResponseAdvice;
import top.moma.zoffy.support.reaponse.Response;
import top.moma.zoffy.support.reaponse.SuccessResponse;

@RestControllerAdvice
public class ZoffyResponseAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(
      MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

    boolean result =
        returnType.getContainingClass().isAnnotationPresent(IgnoreResponseAdvice.class)
            || Objects.requireNonNull(returnType.getMethod())
                .isAnnotationPresent(IgnoreResponseAdvice.class)
            || returnType.getParameterType().isAssignableFrom(Response.class)
            || Response.class.isAssignableFrom(returnType.getParameterType());
    return !result;
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {
    if (request.getURI().getPath().startsWith("/v3/api-docs/")) {
      return body;
    }

    if (body instanceof String) {
      response.getHeaders().add("Content-Type", "application/json");
      return JsonHelper.toJson(SuccessResponse.success(body));
    }
    return SuccessResponse.success(body);
  }
}
