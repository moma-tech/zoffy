package top.moma.zoffy.rabc.presentation.response;

import lombok.Data;

@Data
public class UserResponse implements java.io.Serializable {
  /** 用户ID */
  private Long userId;
  /** 用户名 */
  private String userName;
  /** 用户邮箱 */
  private String userEmail;
  /** 用户手机号 */
  private String userPhone;
}
