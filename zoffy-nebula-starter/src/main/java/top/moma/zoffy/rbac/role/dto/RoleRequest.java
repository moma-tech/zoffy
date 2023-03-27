package top.moma.zoffy.rbac.role.dto;

import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.moma.zoffy.rbac.role.entity.ZoffyRole;

@Getter
@Setter
public class RoleRequest {
  private Long roleId;
  private String roleName;
  private String roleCode;

  @Mapper
  public interface ZoffyRoleMapper {
    ZoffyRoleMapper INSTANCE = Mappers.getMapper(ZoffyRoleMapper.class);

    ZoffyRole roleRequestToZoffyRole(RoleRequest request);
  }
}
