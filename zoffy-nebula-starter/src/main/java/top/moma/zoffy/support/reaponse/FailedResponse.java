package top.moma.zoffy.support.reaponse;

import top.moma.zoffy.common.enumerations.HttpResponseEnum;

public class FailedResponse extends Response {

  public static FailedResponse failed() {
    FailedResponse failedResponse = new FailedResponse();
    failedResponse.setSuccess(false);
    failedResponse.setCode(HttpResponseEnum.INTERNAL_SERVER_ERROR.code());
    failedResponse.setMessage(HttpResponseEnum.INTERNAL_SERVER_ERROR.msg());
    return failedResponse;
  }

  public static FailedResponse failed(HttpResponseEnum httpResponseEnum) {
    FailedResponse failedResponse = new FailedResponse();
    failedResponse.setSuccess(false);
    failedResponse.setCode(httpResponseEnum.code());
    failedResponse.setMessage(httpResponseEnum.msg());
    return failedResponse;
  }

  public static FailedResponse failed(Integer code, String errorMessage) {
    FailedResponse failedResponse = new FailedResponse();
    failedResponse.setSuccess(false);
    failedResponse.setCode(code);
    failedResponse.setMessage(errorMessage);
    return failedResponse;
  }

  public static FailedResponse failed(HttpResponseEnum httpResponseEnum, String errorDetails) {
    FailedResponse failedResponse = new FailedResponse();
    failedResponse.setSuccess(false);
    failedResponse.setCode(httpResponseEnum.code());
    failedResponse.setMessage(httpResponseEnum.msg());
    failedResponse.setErrorDetails(errorDetails);
    return failedResponse;
  }

  public String getErrorDetails() {
    return errorDetails;
  }

  public void setErrorDetails(String errorDetails) {
    this.errorDetails = errorDetails;
  }

  private String errorDetails;
}
