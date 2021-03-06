package top.moma.zoffy.beam.auth.rel.service.impl;

import org.springframework.stereotype.Service;
import top.moma.m78.framework.common.service.impl.SuperServiceImpl;
import top.moma.zoffy.beam.auth.rel.dao.AuthRelRoleResourceDao;
import top.moma.zoffy.beam.auth.rel.model.domain.AuthRelRoleResource;
import top.moma.zoffy.beam.auth.rel.service.AuthRelRoleResourceService;

/**
 * 服务实现类
 *
 * @author Ivan
 * @since 2019-01-29
 */
@Service
public class AuthRelRoleResourceServiceImpl
    extends SuperServiceImpl<AuthRelRoleResourceDao, AuthRelRoleResource>
    implements AuthRelRoleResourceService {}
