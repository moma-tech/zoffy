package top.moma.zoffy.rbac.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.moma.zoffy.rbac.role.entity.Role;
import top.moma.zoffy.rbac.role.mapper.RoleMapper;
import top.moma.zoffy.rbac.role.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {}
