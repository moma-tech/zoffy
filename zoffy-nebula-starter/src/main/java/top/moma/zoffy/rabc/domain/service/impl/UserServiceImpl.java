package top.moma.zoffy.rabc.domain.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.moma.zoffy.rabc.domain.covertor.UserConvertor;
import top.moma.zoffy.rabc.domain.entity.User;
import top.moma.zoffy.rabc.domain.repository.facade.UserRepository;
import top.moma.zoffy.rabc.domain.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Autowired UserRepository userRepository;

  @Override
  public List<User> listUsers() {
    return userRepository.listUsers().stream()
        .map(UserConvertor.INSTANCE::poToEntity)
        .collect(Collectors.toList());
  }
}
