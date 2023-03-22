package top.moma.zoffy.support.reaponse;

import top.moma.m64.core.constants.StringConstants;
import top.moma.zoffy.common.enumerations.HttpResponseEnum;

/**
 * SuccessResponse
 *
 * <p>成功响应
 *
 * @version 1.0
 * @author Created by ivan at 18:00.
 */
public class SuccessResponse<T> extends Response {

  private SuccessResponse() {}

  /**
   * SuccessResponse
   *
   * <p>限制构造
   *
   * @param httpResponseEnum httpResponseEnum
   * @param data data
   * @author Created by ivan
   * @since 2023/3/22 18:02
   */
  public SuccessResponse(HttpResponseEnum httpResponseEnum, T data) {
    setSuccess(true);
    setCode(httpResponseEnum.code());
    setMessage(httpResponseEnum.msg());
    setData(data);
  }

  /**
   * success
   *
   * <p>空结构体响应
   *
   * @return top.moma.zoffy.support.reaponse.SuccessResponse<java.lang.String>
   * @author Created by ivan
   * @since 2023/3/22 18:02
   */
  public static SuccessResponse<String> success() {
    return new SuccessResponse<>(HttpResponseEnum.OK, StringConstants.EMPTY);
  }

  /**
   * success
   *
   * <p>结构体响应
   *
   * @param data data
   * @return top.moma.zoffy.support.reaponse.SuccessResponse<T>
   * @author Created by ivan
   * @since 2023/3/22 18:03
   */
  public static <T> SuccessResponse<T> success(T data) {
    return new SuccessResponse<>(HttpResponseEnum.OK, data);
  }

  /** 响应结构体 */
  private T data;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
