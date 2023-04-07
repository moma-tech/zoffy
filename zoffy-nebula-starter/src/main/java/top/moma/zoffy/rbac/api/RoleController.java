package top.moma.zoffy.rbac.api;

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
import top.moma.zoffy.rbac.dto.role.RoleRequest;
import top.moma.zoffy.rbac.dto.role.RoleResponse;
import top.moma.zoffy.rbac.storage.role.service.RoleService;

@RestController
@RequestMapping("role")
@ZoffyResponse
@Tag(name = "Role Controller")
public class RoleController {
  @Autowired
  RoleService roleService;

  @Operation(summary = "Add Role")
  @PostMapping("/add")
  public RoleResponse addRole(@RequestBody @Valid RoleRequest roleRequest) {
    return roleService.addRole(roleRequest);
  }

  @Operation(summary = "List User")
  @PostMapping("/list")
  public List<RoleResponse> listRole() {
    return roleService.list();
  }
}
