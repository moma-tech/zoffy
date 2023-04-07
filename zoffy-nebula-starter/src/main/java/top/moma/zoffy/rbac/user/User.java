package top.moma.zoffy.rbac.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
  private Long id;
  private String userName;
  private String password;
}
