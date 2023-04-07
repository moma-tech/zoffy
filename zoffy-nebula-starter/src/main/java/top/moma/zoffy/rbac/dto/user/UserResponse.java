package top.moma.zoffy.rbac.dto.user;

import lombok.Data;

@Data
public class UserResponse implements java.io.Serializable {
  private Long id;
  private String userName;
  private String userPhone;
  private String userEmail;
}