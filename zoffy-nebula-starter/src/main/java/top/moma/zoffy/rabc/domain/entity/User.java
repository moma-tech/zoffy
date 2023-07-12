package top.moma.zoffy.rabc.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User implements java.io.Serializable {
  /** 用户ID */
  private Long userId;
  /** 用户名 */
  private String userName;
  /** 用户密码 */
  private String userPassword;
  /** 用户邮箱 */
  private String userEmail;
  /** 用户手机号 */
  private String userPhone;
}
