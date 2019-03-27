package top.moma.zoffy.config;

import io.undertow.Undertow;
import java.util.List;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.moma.m78.framework.customizer.exception.GeneralExceptionHandler;
import top.moma.m78.framework.customizer.server.UndertowServerFactoryCustomizer;
import top.moma.m78.framework.wrapper.HttpMessageConverterWrapper;
import top.moma.zoffy.interception.aspect.LogAspect;

/**
 * SpringMvcConfiguration
 * <p> TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 1/17/19 - 4:37 PM.
 **/
@SpringBootConfiguration
public class SpringMvcConfiguration implements WebMvcConfigurer {

  @Bean
  public LogAspect getLogAspect(){
    return new LogAspect();
  }

  @Bean
  @ConditionalOnClass(Undertow.class)
  public UndertowServerFactoryCustomizer undertowServerFactoryCustomizer() {
    return new UndertowServerFactoryCustomizer();
  }

  @Override
  public void configureHandlerExceptionResolvers(
          List<HandlerExceptionResolver> exceptionResolvers) {
    exceptionResolvers.add(new GeneralExceptionHandler());
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.forEach(HttpMessageConverterWrapper.objectMapperWrapper());
  }

  @Bean
  @ConditionalOnMissingBean(RequestContextListener.class)
  public RequestContextListener requestContextListener() {
    return new RequestContextListener();
  }
}
