package top.moma.zoffy.rbac.resource.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import top.moma.zoffy.rbac.resource.dto.ResourceDtoMapper;
import top.moma.zoffy.rbac.resource.dto.ResourceRequest;
import top.moma.zoffy.rbac.resource.dto.ResourceResponse;
import top.moma.zoffy.rbac.resource.entity.ZoffyResource;
import top.moma.zoffy.rbac.resource.repository.ResourceRepository;

@Service
public class ResourceServiceImpl implements ResourceService {
  @Autowired ResourceRepository resourceRepository;

  @Override
  public List<ResourceResponse> list() {
    return resourceRepository.findAll(Sort.by(Direction.ASC, ZoffyResource.SORT_ORDER)).stream()
        .map(ResourceDtoMapper.INSTANCE::zoffyToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public ResourceResponse addRole(ResourceRequest request) {
    ZoffyResource zoffyResource =
        resourceRepository.save(ResourceDtoMapper.INSTANCE.requestToZoffy(request));
    return ResourceDtoMapper.INSTANCE.zoffyToResponse(zoffyResource);
  }
}
