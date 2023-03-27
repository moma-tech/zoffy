package top.moma.zoffy.rbac.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import top.moma.zoffy.annotation.ValidEmail;
import top.moma.zoffy.rbac.user.entity.ZoffyUser;

@Data
public class UserRequest implements java.io.Serializable {

  @NotNull @NotEmpty private String userName;
  @NotNull @NotEmpty private String password;
  @NotNull @NotEmpty private String phone;
  @NotNull @NotEmpty @ValidEmail private String email;

  private String matchingPassword;

  public ZoffyUser formEntity() {
    ZoffyUser user = new ZoffyUser();
    user.setUserName(userName);
    user.setUserPassword(password);
    user.setUserPhone(phone);
    user.setUserEmail(email);
    return user;
  }
}
