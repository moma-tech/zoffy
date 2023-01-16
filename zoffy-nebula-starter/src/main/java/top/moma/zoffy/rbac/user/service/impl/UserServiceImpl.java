package top.moma.zoffy.rbac.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.moma.zoffy.rbac.role.entity.Role;
import top.moma.zoffy.rbac.user.entity.User;
import top.moma.zoffy.rbac.user.mapper.UserMapper;
import top.moma.zoffy.rbac.user.response.UserInfo;
import top.moma.zoffy.rbac.user.service.UserService;
import top.moma.zoffy.rbac.user_role.entity.UserRole;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
  @Autowired UserMapper userMapper;

  @Override
  public User qById(Integer userId) {
    return userMapper.qById(userId);
  }

  @Override
  public List<UserInfo> getUserInfo() {
    MPJLambdaWrapper<User> mpjLambdaWrapper =
        new MPJLambdaWrapper<User>()
            .selectAll(User.class)
            .select(Role::getRoleName)
            .leftJoin(UserRole.class, UserRole::getUserId, User::getUserId)
            .leftJoin(Role.class, Role::getRoleId, UserRole::getRoleId);
    return userMapper.selectJoinList(UserInfo.class, mpjLambdaWrapper);
  }
}
