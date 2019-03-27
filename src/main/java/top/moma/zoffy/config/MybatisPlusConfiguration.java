package top.moma.zoffy.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * MybatisPlusConfiguration
 *
 * <p>Mybatis Plus Config
 *
 * <p>1. Pagination
 *
 * <p>2. Optimistic Locker
 *
 * @author Ivan
 * @version 1.0 Create by Ivan on 2019/3/27 - 15:53
 */
public class MybatisPlusConfiguration {
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }

  @Bean
  public OptimisticLockerInterceptor optimisticLockerInterceptor() {
    return new OptimisticLockerInterceptor();
  }

  @Bean
  @Profile({"local"}) // 设置 dev test 环境开启
  public PerformanceInterceptor performanceInterceptor() {
    return new PerformanceInterceptor();
  }
}
