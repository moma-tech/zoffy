package top.moma.zoffy.rabc.domain.covertor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.moma.zoffy.rabc.domain.entity.User;
import top.moma.zoffy.rabc.domain.repository.po.UserPo;
import top.moma.zoffy.rabc.presentation.response.UserResponse;

@Mapper
public interface UserConvertor {
  UserConvertor INSTANCE = Mappers.getMapper(UserConvertor.class);

  User poToEntity(UserPo userPo);

  UserResponse entityToResponse(User user);
}
