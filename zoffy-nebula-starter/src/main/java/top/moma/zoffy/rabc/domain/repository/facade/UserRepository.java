package top.moma.zoffy.rabc.domain.repository.facade;

import java.util.List;
import java.util.Optional;
import top.moma.zoffy.rabc.domain.repository.po.UserPo;

public interface UserRepository {

  Optional<UserPo> getUserById(Long userId);

  List<UserPo> listUsers();

  Optional<UserPo> addUser(UserPo userPo);

  Optional<UserPo> updateUser(UserPo userPo);

  Optional<UserPo> deleteUser(UserPo userPo);
}
