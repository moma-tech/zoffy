package top.moma.zoffy.rbac.storage.role.service;

import java.util.List;
import top.moma.zoffy.rbac.dto.role.RoleRequest;
import top.moma.zoffy.rbac.dto.role.RoleResponse;

public interface RoleService {
  List<RoleResponse> list();

  RoleResponse addRole(RoleRequest request);
}
