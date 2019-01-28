package top.moma.zoffy.beam.auth.rel.service.impl;

import top.moma.zoffy.beam.auth.rel.model.domain.AuthRelUserRole;
import top.moma.zoffy.beam.auth.rel.dao.AuthRelUserRoleDao;
import top.moma.zoffy.beam.auth.rel.service.AuthRelUserRoleService;
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
public class AuthRelUserRoleServiceImpl extends SuperServiceImpl<AuthRelUserRoleDao, AuthRelUserRole> implements AuthRelUserRoleService {

}
