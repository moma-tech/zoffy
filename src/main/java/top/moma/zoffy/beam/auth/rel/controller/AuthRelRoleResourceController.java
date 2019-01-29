package top.moma.zoffy.beam.auth.rel.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.m78.framework.common.controller.SuperController;

/**
 * 前端控制器
 *
 * @author Ivan
 * @since 2019-01-29
 */
@RestController
@Api(value = "", tags = "")
@Slf4j
@RequestMapping("/authRelRoleResource")
public class AuthRelRoleResourceController extends SuperController {
  /* Constants Field */

  /* Auto Wire Field  */

  /* Methods */
  /**
   * @author Created by ivan.
   *     <p>//TODO demoApi
   * @param param :
   * @return java.lang.String
   */
  @ApiOperation(value = "", notes = "")
  @PostMapping(
      value = "/",
      produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String demoApi(@RequestBody String param) {

    return param;
  }

  /* Private Methods */

}
