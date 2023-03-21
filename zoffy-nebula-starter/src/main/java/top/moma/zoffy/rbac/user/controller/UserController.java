package top.moma.zoffy.rbac.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.zoffy.rbac.user.controller.request.UserRequest;
import top.moma.zoffy.rbac.user.entity.UserEntity;
import top.moma.zoffy.rbac.user.service.UserService;

@RestController
@RequestMapping("user")
@Tag(name = "User Controller")
public class UserController {

  @Autowired UserService userService;

  @Operation(summary = "Check User Valid")
  @PostMapping("/check")
  public Boolean checkValid(@RequestBody @Valid UserRequest userRequest) {
    boolean isExisted = userService.checkEmailExisted(userRequest.getEmail());
    return !isExisted;
  }

  @Operation(summary = "Add User")
  @PostMapping("/add")
  public UserEntity addUser(@RequestBody @Valid UserRequest userRequest) {
    return userService.addUser(userRequest);
  }
}
