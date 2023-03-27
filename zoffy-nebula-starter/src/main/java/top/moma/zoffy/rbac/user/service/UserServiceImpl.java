package top.moma.zoffy.rbac.user.service;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.moma.zoffy.rbac.user.controller.request.UserRequest;
import top.moma.zoffy.rbac.user.entity.ZoffyUser;
import top.moma.zoffy.rbac.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Override
  public boolean checkEmailExisted(String email) {
    ZoffyUser user = userRepository.findByUserEmail(email);
    return Objects.nonNull(user);
  }

  @Override
  public ZoffyUser addUser(UserRequest userRequest) {
    return userRepository.save(userRequest.formEntity());
  }

  @Override
  public List<ZoffyUser> listUser() {
    return userRepository.findAll();
  }
}
