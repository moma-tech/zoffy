package top.moma.zoffy.rbac.user.exception;

import jakarta.servlet.http.HttpServletResponse;

public enum UserExceptionEnum {
  USER_EMAIL_EXISTED(HttpServletResponse.SC_OK, "Email existed!"),
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
}
