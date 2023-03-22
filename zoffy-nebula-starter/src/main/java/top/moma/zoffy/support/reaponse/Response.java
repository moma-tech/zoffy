package top.moma.zoffy.support.reaponse;

/**
 * Response
 *
 * <p>基础响应对象
 *
 * @version 1.0
 * @author Created by ivan at 17:59.
 */
public class Response implements java.io.Serializable {

  /** 请求处理状态 */
  private Boolean success;
  /** 响应码 */
  private Integer code;
  /** 响应信息 */
  private String message;

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
