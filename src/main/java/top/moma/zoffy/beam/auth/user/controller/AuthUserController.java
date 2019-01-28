package top.moma.zoffy.beam.auth.user.controller;


import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.moma.m78.framework.common.controller.SuperController;
import top.moma.m78.framework.response.domain.SuccessResponse;
import top.moma.zoffy.beam.auth.user.service.AuthUserService;

/**
 * <p>
 * User Basic Identification Info 前端控制器
 * </p>
 *
 * @author Ivan
 * @since 2019-01-28
 */
@RestController
@RequestMapping("/authUser")
public class AuthUserController extends SuperController {

  public static void main(String[] args) {
    for(int i=0; i< 10; i++){
      System.out.println(StringUtils.remove(UUID.randomUUID().toString(),'-').toLowerCase());
    }


  }
}

