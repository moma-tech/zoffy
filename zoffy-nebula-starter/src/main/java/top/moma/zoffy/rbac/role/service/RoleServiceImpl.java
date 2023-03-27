package top.moma.zoffy.rbac.role.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.moma.zoffy.rbac.role.dto.RoleRequest;
import top.moma.zoffy.rbac.role.dto.RoleResponse;
import top.moma.zoffy.rbac.role.entity.ZoffyRole;
import top.moma.zoffy.rbac.role.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
  @Autowired RoleRepository roleRepository;

  @Override
  public List<RoleResponse> list() {
    return roleRepository.findAll().stream()
        .map(RoleResponse.ZoffyRoleMapper.INSTANCE::zoffyRoleToRoleResponse)
        .toList();
  }

  @Override
  public RoleResponse addRole(RoleRequest request) {
    ZoffyRole zoffyRole = RoleRequest.ZoffyRoleMapper.INSTANCE.roleRequestToZoffyRole(request);
    zoffyRole = roleRepository.save(zoffyRole);
    return RoleResponse.ZoffyRoleMapper.INSTANCE.zoffyRoleToRoleResponse(zoffyRole);
  }
}
