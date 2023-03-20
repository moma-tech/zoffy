package top.moma.zoffy.support.reaponse;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import top.moma.zoffy.common.enumerations.HttpResponseEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> implements java.io.Serializable {
  private int code;
  private String message;
  private T data;
  private String error;

  public Response<T> success() {
    return success(HttpResponseEnum.OK.code(), HttpResponseEnum.OK.msg(), null);
  }

  public Response<T> success(String message) {
    return success(HttpResponseEnum.OK.code(), message);
  }

  public Response<T> success(int code, String message) {
    return success(code, message, null);
  }

  public Response<T> success(int code, String message, T data) {
    return generate(code, message, data, null);
  }

  public Response<T> failed() {
    return failed(
        HttpResponseEnum.INTERNAL_SERVER_ERROR.code(),
        HttpResponseEnum.INTERNAL_SERVER_ERROR.msg(),
        null);
  }

  public Response<T> failed(String message) {
    return failed(HttpResponseEnum.INTERNAL_SERVER_ERROR.code(), message);
  }

  public Response<T> failed(int code, String message) {
    return failed(code, message, null);
  }

  public Response<T> failed(int code, String message, String error) {
    return generate(code, message, null, error);
  }

  public Response<T> generate(
      @NotNull int code, @NotNull String message, @Nullable T data, @Nullable String error) {
    return Response.<T>builder().code(code).message(message).data(data).error(error).build();
  }
}
