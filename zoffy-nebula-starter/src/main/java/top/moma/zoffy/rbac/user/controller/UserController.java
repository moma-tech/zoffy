package top.moma.zoffy.rbac.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.zoffy.rbac.user.controller.request.UserRequest;
import top.moma.zoffy.support.reaponse.Response;
import top.moma.zoffy.support.reaponse.ResponseHelper;

@RestController
@RequestMapping("user")
@Tag(name = "User Controller")
public class UserController {

  @Operation(summary = "Check User Valid")
  @PostMapping("check")
  public Response<UserRequest> checkValid(@RequestBody @Valid UserRequest userRequest) {
    return ResponseHelper.COMMON_SUCCESS;
  }
}
