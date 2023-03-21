package top.moma.zoffy.rbac.user.controller.response;

import lombok.Data;

@Data
public class UserResponse implements java.io.Serializable {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private int deleted;
  private String remarks;
}
