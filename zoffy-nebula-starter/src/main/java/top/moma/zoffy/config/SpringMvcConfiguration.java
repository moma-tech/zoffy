package top.moma.zoffy.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.moma.zoffy.support.wrapper.CustomMessageConverterWrapper;

/**
 * SpringMvcConfiguration
 *
 * <p>MVC相关配置
 *
 * @version 1.0
 * @author Created by ivan at 17:31.
 */
@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

  /**
   * 循环替换 configureMessageConverters
   *
   * @param converters converters
   * @author Created by ivan
   * @since 2023/3/22 11:27
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.forEach(CustomMessageConverterWrapper.objectMapperWrapper());
  }

  @Bean
  public ParameterConverters parameterConverters() {
    return new ParameterConverters();
  }
}
