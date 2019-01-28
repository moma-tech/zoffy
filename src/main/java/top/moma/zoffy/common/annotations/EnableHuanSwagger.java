package top.moma.zoffy.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import top.moma.zoffy.config.swagger.HuanSwagger;

/** Created by liuhuan on 2018/7/20 9:47. */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@EnableSwagger2
@Import({HuanSwagger.class})
public @interface EnableHuanSwagger {}
