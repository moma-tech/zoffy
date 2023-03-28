package top.moma.zoffy.rbac.user.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.moma.zoffy.rbac.user.entity.ZoffyUser;

@Mapper
public interface UserDtoMapper {
  UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

  ZoffyUser requestToZoffy(UserRequest request);

  UserResponse zoffyToResponse(ZoffyUser zoffyUser);
}
