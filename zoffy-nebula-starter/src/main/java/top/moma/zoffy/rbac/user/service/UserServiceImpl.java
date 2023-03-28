package top.moma.zoffy.rbac.user.service;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.moma.zoffy.common.constants.GeneralConstants;
import top.moma.zoffy.rbac.user.dto.UserDtoMapper;
import top.moma.zoffy.rbac.user.dto.UserRequest;
import top.moma.zoffy.rbac.user.dto.UserResponse;
import top.moma.zoffy.rbac.user.entity.ZoffyUser;
import top.moma.zoffy.rbac.user.exception.UserExceptionEnum;
import top.moma.zoffy.rbac.user.repository.UserRepository;

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
  public UserResponse addUser(UserRequest userRequest) {
    Assert.isTrue(
        checkEmailValid(userRequest.getUserEmail()), UserExceptionEnum.USER_EMAIL_EXISTED.msg());
    ZoffyUser zoffyUser = userRepository.save(UserDtoMapper.INSTANCE.requestToZoffy(userRequest));
    return UserDtoMapper.INSTANCE.zoffyToResponse(zoffyUser);
  }

  @Override
  public List<UserResponse> listUser() {
    return userRepository.findAll().stream().map(UserDtoMapper.INSTANCE::zoffyToResponse).toList();
  }
}
