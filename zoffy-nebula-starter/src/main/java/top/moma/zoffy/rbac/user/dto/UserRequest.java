package top.moma.zoffy.rbac.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import top.moma.zoffy.annotation.ValidEmail;

@Data
public class UserRequest implements java.io.Serializable {

  @NotNull @NotEmpty private String userName;
  @NotNull @NotEmpty private String userPassword;
  @NotNull @NotEmpty private String userPhone;
  @NotNull @NotEmpty @ValidEmail private String userEmail;

  private String matchingPassword;
}
