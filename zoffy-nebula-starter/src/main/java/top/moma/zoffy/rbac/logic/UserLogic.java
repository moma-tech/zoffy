package top.moma.zoffy.rbac.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.zoffy.rbac.dto.user.UserDtoMapper;
import top.moma.zoffy.rbac.dto.user.UserRequest;
import top.moma.zoffy.rbac.dto.user.UserResponse;
import top.moma.zoffy.rbac.exception.UserExceptionEnum;
import top.moma.zoffy.rbac.storage.resource.entity.ZoffyResource;
import top.moma.zoffy.rbac.storage.resource.service.ResourceService;
import top.moma.zoffy.rbac.storage.role.entity.ZoffyRole;
import top.moma.zoffy.rbac.storage.role.service.RoleService;
import top.moma.zoffy.rbac.storage.user.entity.ZoffyUser;
import top.moma.zoffy.rbac.storage.user.service.UserService;

@Service
public class UserLogic implements UserDetailsService {
  @Autowired UserService userService;
  @Autowired RoleService roleService;
  @Autowired ResourceService resourceService;
  @Autowired PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ZoffyUser zoffyUser = userService.findUserByLogKey(username);
    if (ObjectHelper.isEmpty(zoffyUser)) {
      throw new UsernameNotFoundException(UserExceptionEnum.USER_ACCOUNT_NOT_EXISTED.msg());
    }
    boolean userDisabled = false;
    boolean userExpired = false;
    boolean userLocked = false;

    return User.builder()
        .username(zoffyUser.getUserEmail())
        .password(zoffyUser.getUserPassword())
        .disabled(userDisabled)
        .accountExpired(userExpired)
        .accountLocked(userLocked)
        .authorities(getAuthorities(zoffyUser.getRoleSet()))
        .build();
  }

  /**
   * description getAuthorities
   *
   * <p>build user authorities list
   *
   * @param roleSet roleSet
   * @return java.util.List<org.springframework.security.core.GrantedAuthority>
   * @author Created by ivan
   * @since 2023/4/7 17:20
   */
  private List<GrantedAuthority> getAuthorities(Set<ZoffyRole> roleSet) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    List<String> roleResourceList = roleResourceList(roleSet);
    for (String authority : roleResourceList) {
      authorities.add(new SimpleGrantedAuthority(authority));
    }
    return authorities;
  }

  /**
   * roleResourceList
   *
   * <p>Query User Role and Resource List
   *
   * @param roleSet roleSet
   * @return java.util.List<java.lang.String>
   * @author Created by ivan
   * @since 2023/4/7 17:19
   */
  private List<String> roleResourceList(Set<ZoffyRole> roleSet) {
    List<String> roleResourceList = new ArrayList<>();
    for (ZoffyRole role : roleSet) {
      roleResourceList.add(role.getRoleCode());
      for (ZoffyResource zoffyResource : role.getResourceSet()) {
        roleResourceList.add(zoffyResource.getResourceCode());
      }
    }
    return roleResourceList;
  }

  /**
   * description addUser
   *
   * @param userRequest userRequest
   * @return top.moma.zoffy.rbac.user.dto.UserResponse
   * @author Created by ivan
   * @since 2023/3/28 15:12
   */
  public UserResponse addUser(UserRequest userRequest) {
    Assert.isTrue(
        userService.checkEmailValid(userRequest.getUserEmail()),
        UserExceptionEnum.USER_EMAIL_EXISTED.msg());
    ZoffyUser addUser = UserDtoMapper.INSTANCE.requestToZoffy(userRequest);
    addUser.setUserPassword(passwordEncoder.encode(addUser.getUserPassword()));
    return UserDtoMapper.INSTANCE.zoffyToResponse(userService.addUser(addUser));
  }

  /**
   * description listUser
   *
   * @return java.util.List<top.moma.zoffy.rbac.user.dto.UserResponse>
   * @author Created by ivan
   * @since 2023/3/28 15:12
   */
  public List<UserResponse> listUser() {
    return userService.listUser().stream().map(UserDtoMapper.INSTANCE::zoffyToResponse).toList();
  }

  public UserResponse getUser(String logKey) {
    ZoffyUser zoffyUser = userService.findUserByLogKey(logKey);
    return UserDtoMapper.INSTANCE.zoffyToResponse(zoffyUser);
  }
}
