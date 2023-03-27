package top.moma.zoffy.rbac.resource.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.moma.zoffy.rbac.resource.entity.ZoffyResource;

@Mapper
public interface ResourceDtoMapper {
  ResourceDtoMapper INSTANCE = Mappers.getMapper(ResourceDtoMapper.class);

  ZoffyResource requestToZoffy(ResourceRequest request);

  ResourceResponse zoffyToResponse(ZoffyResource request);
}
