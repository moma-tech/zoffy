package top.moma.zoffy.rbac.user.service;

import java.util.List;
import top.moma.zoffy.rbac.user.dto.UserRequest;
import top.moma.zoffy.rbac.user.entity.ZoffyUser;

public interface UserService {

  /**
   * checkEmailExisted
   *
   * <p>邮箱是否已占用
   *
   * @param email email
   * @return boolean
   * @author Created by ivan
   * @since 2023/3/22 17:56
   */
  boolean checkEmailExisted(String email);

  /**
   * description addUser
   *
   * @param userRequest userRequest
   * @return top.moma.zoffy.rbac.user.entity.ZoffyUser
   * @author Created by ivan
   * @since 2023/3/27 14:21
   */
  ZoffyUser addUser(UserRequest userRequest);

  /**
   * description listUser
   *
   * @return java.util.List<top.moma.zoffy.rbac.user.entity.ZoffyUser>
   * @author Created by ivan
   * @since 2023/3/27 14:21
   */
  List<ZoffyUser> listUser();
}
