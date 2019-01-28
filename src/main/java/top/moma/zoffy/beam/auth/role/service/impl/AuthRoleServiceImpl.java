package top.moma.zoffy.beam.auth.role.service.impl;

import top.moma.zoffy.beam.auth.role.model.domain.AuthRole;
import top.moma.zoffy.beam.auth.role.dao.AuthRoleDao;
import top.moma.zoffy.beam.auth.role.service.AuthRoleService;
import top.moma.m78.framework.common.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ivan
 * @since 2019-01-28
 */
@Service
public class AuthRoleServiceImpl extends SuperServiceImpl<AuthRoleDao, AuthRole> implements AuthRoleService {

}
