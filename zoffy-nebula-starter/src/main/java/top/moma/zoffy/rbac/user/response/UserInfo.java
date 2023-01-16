package top.moma.zoffy.rbac.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserInfo implements java.io.Serializable {
  private String userId;
  private String userName;
  private String phoneNo;
  private String email;
  private String roleName;
}
