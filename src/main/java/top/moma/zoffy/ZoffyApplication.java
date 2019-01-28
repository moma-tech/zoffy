package top.moma.zoffy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.moma.zoffy.common.annotations.EnableHuanSwagger;

/**
 * ZoffyApplication
 * <p> TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 1/17/19 - 4:26 PM.
 **/
@SpringBootApplication
@EnableHuanSwagger
public class ZoffyApplication {
  public static void main(String[] args) {
    SpringApplication.run(ZoffyApplication.class,args);
  }
}
