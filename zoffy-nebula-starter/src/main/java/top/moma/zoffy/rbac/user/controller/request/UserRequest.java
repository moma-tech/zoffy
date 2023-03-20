package top.moma.zoffy.rbac.user.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import top.moma.zoffy.annotation.ValidEmail;

@Data
public class UserRequest implements java.io.Serializable {
  @NotNull @NotEmpty private String firstName;
  @NotNull @NotEmpty private String lastName;
  @NotNull @NotEmpty private String password;
  private String matchingPassword;
  @NotNull @NotEmpty @ValidEmail private String email;
}
