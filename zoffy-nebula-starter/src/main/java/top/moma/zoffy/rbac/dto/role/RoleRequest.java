package top.moma.zoffy.rbac.dto.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
  private Long roleId;
  private String roleName;
  private String roleCode;
}
