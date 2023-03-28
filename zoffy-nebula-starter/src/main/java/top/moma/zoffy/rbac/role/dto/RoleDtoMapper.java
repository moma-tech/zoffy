package top.moma.zoffy.rbac.role.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.moma.zoffy.rbac.role.entity.ZoffyRole;

@Mapper
public interface RoleDtoMapper {
  RoleDtoMapper INSTANCE = Mappers.getMapper(RoleDtoMapper.class);

  ZoffyRole requestToZoffy(RoleRequest request);

  RoleResponse zoffyToResponse(ZoffyRole zoffyRole);
}
