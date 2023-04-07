package top.moma.zoffy.rbac.storage.role.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.moma.zoffy.rbac.dto.role.RoleDtoMapper;
import top.moma.zoffy.rbac.dto.role.RoleRequest;
import top.moma.zoffy.rbac.dto.role.RoleResponse;
import top.moma.zoffy.rbac.storage.role.entity.ZoffyRole;
import top.moma.zoffy.rbac.storage.role.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
  @Autowired RoleRepository roleRepository;

  @Override
  public List<RoleResponse> list() {
    return roleRepository.findAll().stream().map(RoleDtoMapper.INSTANCE::zoffyToResponse).toList();
  }

  @Override
  public RoleResponse addRole(RoleRequest request) {
    ZoffyRole zoffyRole = RoleDtoMapper.INSTANCE.requestToZoffy(request);
    zoffyRole = roleRepository.save(zoffyRole);
    return RoleDtoMapper.INSTANCE.zoffyToResponse(zoffyRole);
  }
}
