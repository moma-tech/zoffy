package top.moma.zoffy.common.exception;

import top.moma.m64.core.constants.StringConstants;
import top.moma.m64.core.helper.StringHelper;

/**
 * BusinessException
 *
 * <p>业务类型异常，补充Code字段，承载业务Enum返回
 *
 * @version 1.0
 * @author Created by ivan at 15:59.
 */
public class BusinessException extends RuntimeException {
  /** 业务异常编码 */
  private final int code;

  public BusinessException(int code, Throwable e) {
    super(e.getClass().getSimpleName() + StringConstants.COLON + e.getMessage());
    this.code = code;
  }

  public BusinessException(int code, String message) {
    super(message);
    this.code = code;
  }

  public BusinessException(int code, String messageTemplate, Object... params) {
    super(StringHelper.format(messageTemplate, params));
    this.code = code;
  }

  public BusinessException(int code, String message, Throwable throwable) {
    super(message, throwable);
    this.code = code;
  }

  public BusinessException(
      int code, Throwable throwable, String messageTemplate, Object... params) {
    super(StringHelper.format(messageTemplate, params), throwable);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
