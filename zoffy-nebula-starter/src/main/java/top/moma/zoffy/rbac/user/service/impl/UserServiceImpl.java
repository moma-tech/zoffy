package top.moma.zoffy.rbac.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.moma.zoffy.rbac.user.entity.User;
import top.moma.zoffy.rbac.user.mapper.UserMapper;
import top.moma.zoffy.rbac.user.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
  @Autowired UserMapper userMapper;

  @Override
  public User qById(Integer userId) {
    return userMapper.qById(userId);
  }
}
