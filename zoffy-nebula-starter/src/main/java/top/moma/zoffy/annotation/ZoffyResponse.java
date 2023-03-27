package top.moma.zoffy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ZoffyResponse
 *
 * <p>返回使用统一封装
 *
 * @version 1.0
 * @author Created by ivan at 16:24.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ZoffyResponse {}