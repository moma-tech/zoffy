package top.moma.zoffy.rbac.user_role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.moma.zoffy.rbac.user_role.entity.UserRole;
import top.moma.zoffy.rbac.user_role.mapper.UserRoleMapper;
import top.moma.zoffy.rbac.user_role.service.UserRoleService;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {}
