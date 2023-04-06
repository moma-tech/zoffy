package top.moma.zoffy.rbac.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.moma.zoffy.annotation.ZoffyResponse;
import top.moma.zoffy.rbac.user.dto.UserRequest;
import top.moma.zoffy.rbac.user.dto.UserResponse;
import top.moma.zoffy.rbac.user.service.UserService;

@RestController
@RequestMapping("user")
@ZoffyResponse
@Tag(name = "User Controller")
public class UserController {

  @Autowired UserService userService;

  @Operation(summary = "Check User Valid")
  @PostMapping("/check")
  public Boolean checkValid(@RequestBody @Valid UserRequest userRequest) {
    boolean isExisted = userService.checkEmailValid(userRequest.getUserEmail());
    return !isExisted;
  }

  @Operation(summary = "List User")
  @PostMapping("/list")
  public List<UserResponse> listUser() {
    return userService.listUser();
  }

  /**
   * register UserAccount
   *
   * @param userRequest userRequest
   * @return top.moma.zoffy.rbac.user.dto.UserResponse
   * @author Created by ivan
   * @since 2023/4/6 11:30
   */
  @Operation(summary = "User Account Registration")
  @PostMapping("/registration")
  public UserResponse registerUserAccount(@RequestBody @Valid UserRequest userRequest) {
    return userService.addUser(userRequest);
  }

  @Operation(summary = "Get")
  @PostMapping("/get")
  public UserDetails loadUserDetail(@RequestParam String email) {
    return userService.getUser(email);
  }
}
