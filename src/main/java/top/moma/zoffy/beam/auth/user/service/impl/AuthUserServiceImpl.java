package top.moma.zoffy.beam.auth.user.service.impl;

import top.moma.zoffy.beam.auth.user.model.domain.AuthUser;
import top.moma.zoffy.beam.auth.user.dao.AuthUserDao;
import top.moma.zoffy.beam.auth.user.service.AuthUserService;
import top.moma.m78.framework.common.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * User Basic Identification Info 服务实现类
 * </p>
 *
 * @author Ivan
 * @since 2019-01-28
 */
@Service
public class AuthUserServiceImpl extends SuperServiceImpl<AuthUserDao, AuthUser> implements AuthUserService {

}
