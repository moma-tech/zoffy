package top.moma.zoffy.rabc.infrastrcuture.database.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import top.moma.zoffy.rabc.domain.repository.facade.UserRepository;
import top.moma.zoffy.rabc.domain.repository.po.UserPo;
import top.moma.zoffy.rabc.framework.constants.GeneralConstants;
import top.moma.zoffy.rabc.infrastrcuture.database.dao.UserMapper;

@Service
public class UserRepositoryMybatisPlus extends ServiceImpl<UserMapper, UserPo>
    implements UserRepository {

  @Override
  public Optional<UserPo> getUserById(Long userId) {
    return Optional.ofNullable(getById(userId));
  }

  @Override
  public List<UserPo> listUsers() {
    QueryWrapper<UserPo> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq(UserPo.COL_DELETE_MARK, GeneralConstants.DB_DATA_AVAILABLE);
    queryWrapper.orderByDesc(UserPo.COL_CREATE_TIME);
    queryWrapper.last("limit 1");
    return list(queryWrapper);
  }

  @Override
  public Optional<UserPo> addUser(UserPo userPo) {
    save(userPo);
    return Optional.empty();
  }

  @Override
  public Optional<UserPo> updateUser(UserPo userPo) {
    return Optional.empty();
  }

  @Override
  public Optional<UserPo> deleteUser(UserPo userPo) {
    return Optional.empty();
  }
}
