package top.moma.zoffy.rbac.user.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.moma.zoffy.rbac.user.entity.User;

@Mapper
public interface UserMapper extends MPJBaseMapper<User> {

  User qById(final Integer userId);
}
