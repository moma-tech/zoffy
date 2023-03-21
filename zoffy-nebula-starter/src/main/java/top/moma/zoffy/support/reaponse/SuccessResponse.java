package top.moma.zoffy.support.reaponse;

import top.moma.zoffy.common.enumerations.HttpResponseEnum;

public class SuccessResponse<T> extends Response {

  private SuccessResponse() {
  }

  public SuccessResponse(HttpResponseEnum httpResponseEnum, T data) {
    setSuccess(true);
    setCode(httpResponseEnum.code());
    setMessage(httpResponseEnum.msg());
    setData(data);
  }

  public static SuccessResponse<String> success() {
    return new SuccessResponse<>(HttpResponseEnum.OK, "");
  }

  public static <T> SuccessResponse<T> success(T data) {
    return new SuccessResponse<>(HttpResponseEnum.OK, data);
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  private T data;
}
