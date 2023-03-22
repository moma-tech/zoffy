package top.moma.zoffy.rbac.user.service;

import java.util.List;
import top.moma.zoffy.rbac.user.controller.request.UserRequest;
import top.moma.zoffy.rbac.user.entity.UserEntity;

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
   * addUser
   *
   * <p>新增数据
   *
   * @param userRequest userRequest
   * @return top.moma.zoffy.rbac.user.entity.UserEntity
   * @author Created by ivan
   * @since 2023/3/22 17:57
   */
  UserEntity addUser(UserRequest userRequest);

  /**
   * description listUser
   *
   * <p>数据列表
   *
   * @return java.util.List<top.moma.zoffy.rbac.user.entity.UserEntity>
   * @author Created by ivan
   * @since 2023/3/22 17:57
   */
  List<UserEntity> listUser();
}
