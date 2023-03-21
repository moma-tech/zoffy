package top.moma.zoffy.rbac.user.service;

import top.moma.zoffy.rbac.user.controller.request.UserRequest;
import top.moma.zoffy.rbac.user.entity.UserEntity;

public interface UserService {
  boolean checkEmailExisted(String email);

  UserEntity addUser(UserRequest userRequest);
}
