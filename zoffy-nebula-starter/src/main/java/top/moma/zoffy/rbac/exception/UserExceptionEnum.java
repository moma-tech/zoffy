package top.moma.zoffy.rbac.exception;

import jakarta.servlet.http.HttpServletResponse;
import top.moma.zoffy.common.exception.BusinessException;

public enum UserExceptionEnum {
  USER_EMAIL_EXISTED(HttpServletResponse.SC_OK, "Email existed!"),
  USER_ACCOUNT_NOT_EXISTED(HttpServletResponse.SC_OK, "User Account Not Existed!"),
  ;
  /** Code */
  private final int code;
  /** Message */
  private final String msg;

  UserExceptionEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int code() {
    return code;
  }

  public String msg() {
    return msg;
  }

  public static BusinessException userExceptions(UserExceptionEnum userExceptionEnum) {
    return new BusinessException(userExceptionEnum.code(), userExceptionEnum.msg());
  }
}
