package top.moma.zoffy.rbac.user.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.moma.zoffy.rbac.user.entity.User;

import java.util.List;

@Mapper
public interface UserMapper extends MPJBaseMapper<User> {

  User qById(final Integer userId);

  List<User> twoUsers(final Integer userId);

  IPage<User> twoUsersPage(IPage<?> page, Integer phoneNo);
}
