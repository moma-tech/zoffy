package top.moma.zoffy.rbac.storage.resource.service;

import java.util.List;
import top.moma.zoffy.rbac.dto.resource.ResourceRequest;
import top.moma.zoffy.rbac.dto.resource.ResourceResponse;

public interface ResourceService {
  List<ResourceResponse> list();

  ResourceResponse addRole(ResourceRequest request);
}
