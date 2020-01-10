package com;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component //除了加上@Aspect外 还需要声明为spring的组件 @Aspect只是一个切面声明
public class AopTest {
  /**
   * 使用 || , or  表示或
   * 使用 && , and 表示与
   * ! 表示非
   */
  @Pointcut("execution( * com.ControllerTest.* (..))")
  private void pointCut() {
  }

  @Before("pointCut()")
  public void before(JoinPoint joinPoint) {
    //获取节点名称
    String name = joinPoint.getSignature().getName();
    Object[] args = joinPoint.getArgs();
    System.out.println(name + "方法调用前：获取调用参数" + Arrays.toString(args));
  }

  // returning 参数用于指定返回结果与哪一个参数绑定
  @AfterReturning(pointcut = "pointCut()", returning = "result")
  public String afterReturning(JoinPoint joinPoint, Object result) {
    System.out.println("后置返回通知结果" + result);
    return result+""+"ZJC";
  }

  @Around("pointCut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("环绕通知-前");
    //调用目标方法
    Object proceed = joinPoint.proceed();
    Map map = JSONObject.parseObject(proceed+"",Map.class);
    map.put("3","ZProcess");
    System.out.println("环绕通知-后"+proceed);
    return JSON.toJSONString(map);
  }

  // thrwing 参数用于指定抛出的异常与哪一个参数绑定
  @AfterThrowing(pointcut = "pointCut()", throwing = "exception")
  public void afterThrowing(JoinPoint joinPoint, Exception exception) {
    System.err.println("后置异常通知:" + exception);
  }

  @After("pointCut()")
  public void after(JoinPoint joinPoint) {
    System.out.println("后置通知");
  }
}
