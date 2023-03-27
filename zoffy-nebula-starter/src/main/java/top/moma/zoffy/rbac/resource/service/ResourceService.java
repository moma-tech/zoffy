package top.moma.zoffy.rbac.resource.service;

import java.util.List;
import top.moma.zoffy.rbac.resource.dto.ResourceRequest;
import top.moma.zoffy.rbac.resource.dto.ResourceResponse;

public interface ResourceService {
  List<ResourceResponse> list();

  ResourceResponse addRole(ResourceRequest request);
}
