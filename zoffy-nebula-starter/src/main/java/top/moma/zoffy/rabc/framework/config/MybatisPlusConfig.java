package top.moma.zoffy.rabc.framework.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import java.util.function.Supplier;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import top.moma.zoffy.rabc.infrastrcuture.helper.tenant.TenantHolder;

@SpringBootConfiguration
public class MybatisPlusConfig {

  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    // 租户插件
    interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(zoffyTenantLineHandler().get()));
    // 分页插件
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
    // 乐观锁插件
    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
    return interceptor;
  }

  public Supplier<TenantLineHandler> zoffyTenantLineHandler() {
    return () -> (TenantLineHandler) () -> new LongValue(TenantHolder.getCurrentTenant());
  }
}
