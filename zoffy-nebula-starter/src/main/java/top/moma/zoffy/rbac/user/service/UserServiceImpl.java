package top.moma.zoffy.rbac.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.zoffy.common.constants.GeneralConstants;
import top.moma.zoffy.rbac.role.entity.ZoffyRole;
import top.moma.zoffy.rbac.user.dto.UserDtoMapper;
import top.moma.zoffy.rbac.user.dto.UserRequest;
import top.moma.zoffy.rbac.user.dto.UserResponse;
import top.moma.zoffy.rbac.user.entity.ZoffyUser;
import top.moma.zoffy.rbac.user.exception.UserExceptionEnum;
import top.moma.zoffy.rbac.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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

  @Override
  public UserDetails getUser(String email) {
    return loadUserByUsername(email);
  }

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    ZoffyUser zoffyUser =
        userRepository.findByUserEmailAndDeleteMarkOrUserPhoneAndDeleteMark(
            login, GeneralConstants.DB_DATA_AVAILABLE, login, GeneralConstants.DB_DATA_AVAILABLE);
    if (ObjectHelper.isEmpty(zoffyUser)) {
      throw new UsernameNotFoundException(UserExceptionEnum.USER_ACCOUNT_NOT_EXISTED.msg());
    }
    return User.builder()
        .username(zoffyUser.getUserEmail())
        .password(zoffyUser.getUserPassword())
        .disabled(false)
        .accountExpired(false)
        .accountLocked(false)
        .authorities(getAuthorities(zoffyUser.getRoleSet()))
        .passwordEncoder(NoOpPasswordEncoder.getInstance()::encode)
        .build();
  }

  private List<GrantedAuthority> getAuthorities(Set<ZoffyRole> roleSet) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (ZoffyRole role : roleSet) {
      authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
    }
    authorities.add(new SimpleGrantedAuthority("/user/get"));
    return authorities;
  }
}
