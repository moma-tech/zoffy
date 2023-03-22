package top.moma.zoffy.support.reaponse;

import top.moma.zoffy.common.enumerations.HttpResponseEnum;

/**
 * FailedResponse
 *
 * <p>失败响应
 *
 * @version 1.0
 * @author Created by ivan at 17:57.
 */
public class FailedResponse extends Response {

  /**
   * failed
   *
   * <p>默认响应500
   *
   * @return top.moma.zoffy.support.reaponse.FailedResponse
   * @author Created by ivan
   * @since 2023/3/22 17:57
   */
  public static FailedResponse failed() {
    FailedResponse failedResponse = new FailedResponse();
    failedResponse.setSuccess(false);
    failedResponse.setCode(HttpResponseEnum.INTERNAL_SERVER_ERROR.code());
    failedResponse.setMessage(HttpResponseEnum.INTERNAL_SERVER_ERROR.msg());
    return failedResponse;
  }

  /**
   * failed
   *
   * <p>基于HTTP状态响应
   *
   * @param httpResponseEnum httpResponseEnum
   * @return top.moma.zoffy.support.reaponse.FailedResponse
   * @author Created by ivan
   * @since 2023/3/22 17:58
   */
  public static FailedResponse failed(HttpResponseEnum httpResponseEnum) {
    FailedResponse failedResponse = new FailedResponse();
    failedResponse.setSuccess(false);
    failedResponse.setCode(httpResponseEnum.code());
    failedResponse.setMessage(httpResponseEnum.msg());
    return failedResponse;
  }

  /**
   * failed
   *
   * <p>自定义code/message
   *
   * @param code code
   * @param errorMessage errorMessage
   * @return top.moma.zoffy.support.reaponse.FailedResponse
   * @author Created by ivan
   * @since 2023/3/22 17:58
   */
  public static FailedResponse failed(Integer code, String errorMessage) {
    FailedResponse failedResponse = new FailedResponse();
    failedResponse.setSuccess(false);
    failedResponse.setCode(code);
    failedResponse.setMessage(errorMessage);
    return failedResponse;
  }

  /**
   * failed
   *
   * <p>基于HTTP状态，附加错误信息
   *
   * @param httpResponseEnum httpResponseEnum
   * @param errorDetails errorDetails
   * @return top.moma.zoffy.support.reaponse.FailedResponse
   * @author Created by ivan
   * @since 2023/3/22 17:58
   */
  public static FailedResponse failed(HttpResponseEnum httpResponseEnum, String errorDetails) {
    FailedResponse failedResponse = new FailedResponse();
    failedResponse.setSuccess(false);
    failedResponse.setCode(httpResponseEnum.code());
    failedResponse.setMessage(httpResponseEnum.msg());
    failedResponse.setErrorDetails(errorDetails);
    return failedResponse;
  }

  /** 额外的错误信息 */
  private String errorDetails;

  public String getErrorDetails() {
    return errorDetails;
  }

  public void setErrorDetails(String errorDetails) {
    this.errorDetails = errorDetails;
  }
}
