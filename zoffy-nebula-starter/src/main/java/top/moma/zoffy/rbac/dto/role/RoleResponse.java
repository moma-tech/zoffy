package top.moma.zoffy.rbac.dto.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponse {
  private Long roleId;
  private String roleName;
  private String roleCode;
  private Long parentRoleId;
}
