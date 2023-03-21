package top.moma.zoffy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ZoffyStartUp {

  public static void main(String[] args) {
    SpringApplication.run(ZoffyStartUp.class, args);
  }

  //  @Bean
  //  public LocaleResolver localeResolver() {
  //    SessionLocaleResolver slr = new SessionLocaleResolver();
  //    slr.setDefaultLocale(Locale.ENGLISH);
  //    return slr;
  //  }
}
