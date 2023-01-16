package top.moma.zoffy.rbac.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.moma.zoffy.rbac.user.entity.User;
import top.moma.zoffy.rbac.user.response.UserInfo;

import java.util.List;

public interface UserService extends IService<User> {

  User qById(Integer userId);

  List<UserInfo> getUserInfo();
}
