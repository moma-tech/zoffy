package top.moma.zoffy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.moma.zoffy.rbac.*.mapper")
public class ZoffyStartUp {

  public static void main(String[] args) {
    SpringApplication.run(ZoffyStartUp.class, args);
  }
}
