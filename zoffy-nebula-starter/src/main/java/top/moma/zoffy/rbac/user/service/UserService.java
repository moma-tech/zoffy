package top.moma.zoffy.rbac.user.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import top.moma.zoffy.rbac.user.dto.UserRequest;
import top.moma.zoffy.rbac.user.dto.UserResponse;

public interface UserService {

  /**
   * checkEmailValid
   *
   * <p>邮箱是否可用
   *
   * @param email email
   * @return boolean
   * @author Created by ivan
   * @since 2023/3/22 17:56
   */
  boolean checkEmailValid(String email);

  /**
   * description addUser
   *
   * @param userRequest userRequest
   * @return top.moma.zoffy.rbac.user.dto.UserResponse
   * @author Created by ivan
   * @since 2023/3/28 15:12
   */
  UserResponse addUser(UserRequest userRequest);

  /**
   * description listUser
   *
   * @return java.util.List<top.moma.zoffy.rbac.user.dto.UserResponse>
   * @author Created by ivan
   * @since 2023/3/28 15:12
   */
  List<UserResponse> listUser();

  UserDetails getUser(String email);
}
