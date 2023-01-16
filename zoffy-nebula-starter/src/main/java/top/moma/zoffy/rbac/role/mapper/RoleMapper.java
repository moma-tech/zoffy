package top.moma.zoffy.rbac.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.moma.zoffy.rbac.role.entity.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {}
