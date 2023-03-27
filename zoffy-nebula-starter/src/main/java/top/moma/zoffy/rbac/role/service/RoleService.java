package top.moma.zoffy.rbac.role.service;

import java.util.List;
import top.moma.zoffy.rbac.role.dto.RoleRequest;
import top.moma.zoffy.rbac.role.dto.RoleResponse;

public interface RoleService {
  List<RoleResponse> list();

  RoleResponse addRole(RoleRequest request);
}
