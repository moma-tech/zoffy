package top.moma.zoffy.beam.auth.resource.service.impl;

import top.moma.zoffy.beam.auth.resource.model.domain.AuthResource;
import top.moma.zoffy.beam.auth.resource.dao.AuthResourceDao;
import top.moma.zoffy.beam.auth.resource.service.AuthResourceService;
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
public class AuthResourceServiceImpl extends SuperServiceImpl<AuthResourceDao, AuthResource> implements AuthResourceService {

}
