package top.moma.zoffy.rabc.application.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import top.moma.zoffy.rabc.application.UserApplication;
import top.moma.zoffy.rabc.domain.entity.User;
import top.moma.zoffy.rabc.domain.service.UserService;

@Service
public class UserApplicationImpl implements UserApplication {

  final UserService userService;

  public UserApplicationImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public List<User> listUser() {
    return userService.listUsers();
  }
}
