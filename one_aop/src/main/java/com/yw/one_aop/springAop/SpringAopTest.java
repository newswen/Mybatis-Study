package com.yw.one_aop.springAop;

import com.alibaba.fastjson2.JSON;
import com.yw.one_aop.annation.AspectTest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 说明
 *
 * @author: yuanwen
 * @since: 2024/10/21
 */
@Aspect
@Component
@Slf4j
public class SpringAopTest {

    //定义切入点
    @Pointcut("execution(* com.yw.one_aop.service..*(..))")
    public void pointCut() {}


    //定义环绕通知advice
//    @Around("@annotation(aspectTest)")
    @Around("pointCut()")
    public String around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before");
        //目标执行方法
        Object result = joinPoint.proceed();
        System.out.println("目标执行方法结果:" + JSON.toJSONString(result));
        System.out.println("around after");
        return "最终结果由代理切面类内逻辑决定";
    }

}
