package top.moma.zoffy.rbac.role.dto;

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
