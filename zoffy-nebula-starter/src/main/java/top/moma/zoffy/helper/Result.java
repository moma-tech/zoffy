package top.moma.zoffy.helper;

import java.io.Serializable;

/**
 * 通用返回值
 *
 * @author shuzongrui
 * @create 2018-09-01
 */
public class Result<T> implements Serializable {

  // 默认成功状态吗
  public static final String SUCCESS_CODE = "00000";

  public static final String FAIL_NETWORK_ERROR_CODE = "00001";
  public static final String FAIL_LINK_ERROR_CODE = "00002";
  public static final String FAIL_SYSTEM_ERROR_CODE = "00003";
  public static final String FAIL_INVALID_REQUEST_CODE = "00004";

  public static final String FAIL_CODE = FAIL_SYSTEM_ERROR_CODE;

  public static final String DEFAULT_MESSAGE_SUCCESS = "SUCCESSFUL";
  public static final String FAILED_NETWORK_ERROR_MESSAGE = "NETWORK ERROR!";
  public static final String FAILED_LINK_ERROR_MESSAGE = "NETWORK LINK ERROR!";
  public static final String FAILED_SYSTEM_ERROR_MESSAGE = "SYSTEM ERROR";
  public static final String FAILED_INVALID_REQUEST_MESSAGE = "INVALID REQUEST";

  // 默认成功直接返回
  public static final Result SUCCESSED = new ResultFacade(SUCCESS_CODE, DEFAULT_MESSAGE_SUCCESS);
  // 默认失败是系统故障
  public static final Result FAILED = new ResultFacade(FAIL_CODE, FAILED_SYSTEM_ERROR_MESSAGE);

  public static final Result FAILED_NETWORK_ERROR =
      new ResultFacade(FAIL_NETWORK_ERROR_CODE, FAILED_NETWORK_ERROR_MESSAGE);
  public static final Result FAILED_LINK_ERROR =
      new ResultFacade(FAIL_LINK_ERROR_CODE, FAILED_LINK_ERROR_MESSAGE);
  public static final Result FAILED_INVALID_REQUEST =
      new ResultFacade(FAIL_INVALID_REQUEST_CODE, FAILED_INVALID_REQUEST_MESSAGE);

  public Result() {
    this(null, null, null);
  }

  public Result(String code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  /**
   * The status code, Marking success or failed for the action Usually, this is a ajax action
   * Anthor, maybe the code are 1001, 1002, and so on
   */
  private String code = SUCCESS_CODE;

  /**
   * The message that usually will be showed on the web client Usually, this is a message for a
   * failed ajax action
   */
  private String message;

  /**
   * Data will be a Map or List or Java Bean ...... Sometimes, data also may be a complex Json Style
   * Object such as Map<String,List<SomeBean>> ......
   */
  private T data;

  /** The metadata with the rpc version, response timestamp ...... */
  //    private Metadata metadata;

  /**
   * You should call this method when the action is executed successfully
   *
   * @return Result that contain the success status code and the return data (data)
   */
  public Result<T> success() {
    return success(SUCCESS_CODE, DEFAULT_MESSAGE_SUCCESS);
  }

  /**
   * @param data a Object that contain the specific data
   * @return Result
   */
  public Result<T> success(T data) {
    return success(SUCCESS_CODE, DEFAULT_MESSAGE_SUCCESS, data);
  }

  /**
   * @param code
   * @param message a String Object that contain the specific message for current code
   * @return
   */
  public Result<T> success(String code, String message) {
    return success(code, message, null);
  }

  /**
   * @param code
   * @param message
   * @param data data a Object that contain the specific data for return
   * @return
   */
  public Result<T> success(String code, String message, T data) {
    return setCode(code).setMessage(message).setData(data);
  }

  /**
   * You should call this method when the action is failed. usually, the failed with a Exception
   *
   * @param message a String Object that contain the specific failed message
   * @return Result that contain the failed status code and the null failed message
   */
  public Result<T> failed(String message) {
    return failed(FAIL_CODE, message, null);
  }

  /**
   * You should call this method when the action is failed. usually, the failed with a Exception
   *
   * @param code the user-defined code
   * @param message a String Object that contain the specific failed message
   * @return Result
   */
  public Result<T> failed(String code, String message) {
    return failed(code, message, null);
  }

  /**
   * You should call this method when the action is failed. usually, the failed with a Exception
   *
   * @param code
   * @param message
   * @param data
   * @return
   */
  public Result<T> failed(String code, String message, T data) {
    return setCode(code).setMessage(message).setData(data);
  }
  //
  //  public Result<T> failed(ExceptionEnum exceptionEnum) {
  //    return failed(exceptionEnum.getCode(), exceptionEnum.getMsg());
  //  }

  // getter and setter
  public String getCode() {
    return code;
  }

  public Result<T> setCode(String code) {
    this.code = code;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public Result<T> setMessage(String message) {
    this.message = message;
    return this;
  }

  public T getData() {
    return data;
  }

  public Result<T> setData(T data) {
    this.data = data;
    return this;
  }

  //    public Metadata getMetadata() {
  //        return metadata;
  //    }
  //
  //    public Result<T> setMetadata(Metadata metadata) {
  //        this.metadata = metadata;
  //        return this;
  //    }

  @Override
  public String toString() {
    return "Result{"
        + "code='"
        + code
        + '\''
        + ", message='"
        + message
        + '\''
        + ", data="
        + data
        +
        //                ", metadata=" + metadata +
        '}';
  }

  /** supply a default result */
  private static class ResultFacade extends Result<String> {
    private static final String PLACEHOLDER = "";
    private final String code;
    private final String message;

    public ResultFacade(String code, String message) {
      super();
      this.code = code;
      this.message = message;
    }

    @Override
    public String getCode() {
      return code;
    }

    @Override
    public Result setCode(String code) {
      throw new UnsupportedOperationException("not supported for this operation!");
    }

    @Override
    public Result setMessage(String message) {
      throw new UnsupportedOperationException("not supported for this operation!");
    }

    @Override
    public String getMessage() {
      return message;
    }

    @Override
    public Result setData(String data) {
      throw new UnsupportedOperationException("not supported for this operation!");
    }

    @Override
    public String getData() {
      return PLACEHOLDER;
    }
  }
}
