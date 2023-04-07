package top.moma.zoffy.rbac.storage.resource.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import top.moma.zoffy.rbac.storage.resource.repository.ResourceRepository;
import top.moma.zoffy.rbac.dto.resource.ResourceDtoMapper;
import top.moma.zoffy.rbac.dto.resource.ResourceRequest;
import top.moma.zoffy.rbac.dto.resource.ResourceResponse;
import top.moma.zoffy.rbac.storage.resource.entity.ZoffyResource;

@Service
public class ResourceServiceImpl implements ResourceService {
  @Autowired
  ResourceRepository resourceRepository;

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
