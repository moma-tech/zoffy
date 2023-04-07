package top.moma.zoffy.rbac.storage.user.service;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.moma.zoffy.common.constants.GeneralConstants;
import top.moma.zoffy.rbac.storage.user.entity.ZoffyUser;
import top.moma.zoffy.rbac.storage.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Override
  public boolean checkEmailValid(String email) {
    ZoffyUser user =
        userRepository.findByUserEmailAndDeleteMark(email, GeneralConstants.DB_DATA_AVAILABLE);
    return Objects.isNull(user);
  }

  @Override
  public ZoffyUser addUser(ZoffyUser userRequest) {
    return userRepository.save(userRequest);
  }

  @Override
  public List<ZoffyUser> listUser() {
    return userRepository.findAll();
  }

  @Override
  public ZoffyUser findUserByLogKey(String login) {
    return userRepository.findByUserEmailAndDeleteMarkOrUserPhoneAndDeleteMark(
        login, GeneralConstants.DB_DATA_AVAILABLE, login, GeneralConstants.DB_DATA_AVAILABLE);
  }
}
