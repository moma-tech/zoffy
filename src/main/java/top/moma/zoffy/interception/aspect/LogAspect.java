package top.moma.zoffy.interception.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import top.moma.m78.framework.helper.LogHelper;

/**
 * LogAspect
 * <p> TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 1/17/19 - 4:39 PM.
 **/
@Aspect
public class LogAspect {

  @Pointcut(
      "(execution(public * top.moma..*Controller.*(..)))||(execution(public * top.moma..*Api.*(..)))")
  public void pointCut() {}

  @AfterReturning(returning = "result", pointcut = "pointCut()")
  public void doAfterReturning(Object result) {
    LogHelper.doAfterReturning(result);
  }
}
