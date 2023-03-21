package top.moma.zoffy.rbac.user.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import top.moma.zoffy.annotation.PasswordMatches;
import top.moma.zoffy.annotation.ValidEmail;
import top.moma.zoffy.rbac.user.entity.UserEntity;

@Data
@PasswordMatches
public class UserRequest implements java.io.Serializable {
  @NotNull @NotEmpty private String firstName;
  @NotNull @NotEmpty private String lastName;
  @NotNull @NotEmpty private String password;
  @NotNull @NotEmpty @ValidEmail private String email;
  private String matchingPassword;

  public UserEntity formEntity() {
    UserEntity user = new UserEntity();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPassword(password);
    return user;
  }
}
