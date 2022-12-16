package top.moma.zoffy.rbac.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.moma.zoffy.rbac.user.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

  User qById(final Integer userId);
}
