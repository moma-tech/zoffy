package top.moma.zoffy.config;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.moma.zoffy.support.wrapper.CustomMessageConverterWrapper;

@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.forEach(CustomMessageConverterWrapper.objectMapperWrapper());
  }
}
