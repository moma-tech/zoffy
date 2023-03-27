package top.moma.zoffy.rbac.resource.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.zoffy.annotation.ZoffyResponse;
import top.moma.zoffy.rbac.resource.dto.ResourceRequest;
import top.moma.zoffy.rbac.resource.dto.ResourceResponse;
import top.moma.zoffy.rbac.resource.service.ResourceService;

@RestController
@RequestMapping("resource")
@ZoffyResponse
@Tag(name = "Resource Controller")
public class ResourceController {
  @Autowired ResourceService resourceService;

  @Operation(summary = "Add Resource")
  @PostMapping("/add")
  public ResourceResponse addResource(@RequestBody @Valid ResourceRequest resourceRequest) {
    return resourceService.addRole(resourceRequest);
  }

  @Operation(summary = "List Resource")
  @PostMapping("/list")
  public List<ResourceResponse> listResource() {
    return resourceService.list();
  }
}
