package top.moma.zoffy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * IgnoreResponseAdvice
 *
 * <p>忽略返回封装
 *
 * <p>作用于指定方法
 *
 * @version 1.0
 * @author Created by ivan at 16:24.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface IgnoreResponseAdvice {}
