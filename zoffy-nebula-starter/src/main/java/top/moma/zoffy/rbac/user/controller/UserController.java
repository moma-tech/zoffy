package top.moma.zoffy.rbac.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.moma.zoffy.rbac.user.entity.User;
import top.moma.zoffy.rbac.user.response.UserInfo;
import top.moma.zoffy.rbac.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired UserService userService;

  @PostMapping("/add")
  public User addUser(@RequestBody User user) {
    boolean saveOrUpdate = userService.save(user);
    if (saveOrUpdate) {
      QueryWrapper<User> queryWrapper = new QueryWrapper<>();
      user = userService.getOne(queryWrapper.eq("user_name", user.getUserName()));
    }
    return user;
  }

  @GetMapping("/list")
  public List<UserInfo> listUser() {
    return userService.getUserInfo();
  }

  @GetMapping("/qById")
  public User qUser() {
    return userService.qById(74);
  }
}
