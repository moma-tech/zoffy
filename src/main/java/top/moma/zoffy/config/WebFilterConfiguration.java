package top.moma.zoffy.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import top.moma.m78.framework.interception.filter.RequestFilter;

/**
 * WebFilterConfiguration
 *
 * <p>Filter Configuration
 *
 * @author ivan
 * @version 1.0 Created by ivan on 1/17/19 - 4:36 PM.
 */
@SpringBootConfiguration
public class WebFilterConfiguration {

  @Bean
  public FilterRegistrationBean<RequestFilter> requestFilterRegistrationBean() {
    FilterRegistrationBean<RequestFilter> filterFilterRegistrationBean =
        new FilterRegistrationBean<>();
    filterFilterRegistrationBean.setFilter(this.getRequestFilter());
    filterFilterRegistrationBean.addUrlPatterns("/*");
    filterFilterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 2);
    return filterFilterRegistrationBean;
  }

  @Bean
  public RequestFilter getRequestFilter() {
    return new RequestFilter();
  }
}
