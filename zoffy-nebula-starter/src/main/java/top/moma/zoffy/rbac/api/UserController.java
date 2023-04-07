package top.moma.zoffy.rbac.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.moma.zoffy.annotation.ZoffyResponse;
import top.moma.zoffy.rbac.dto.user.UserRequest;
import top.moma.zoffy.rbac.dto.user.UserResponse;
import top.moma.zoffy.rbac.logic.UserLogic;

@RestController
@RequestMapping("user")
@ZoffyResponse
@Tag(name = "User Controller")
public class UserController {

  @Autowired UserLogic userLogic;

  @Operation(summary = "List User")
  @PostMapping("/list")
  public List<UserResponse> listUser() {
    return userLogic.listUser();
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
    return userLogic.addUser(userRequest);
  }

  @Operation(summary = "Get")
  @PostMapping("/get")
  public UserResponse loadUserDetail(@RequestParam String email) {
    return userLogic.getUser(email);
  }
}
