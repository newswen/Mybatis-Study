package com.yw.one_aop.aop.framework;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 自定义方法拦截器
 *
 * @author: yuanwen
 * @since: 2024/10/22
 */
@Slf4j
public class UserMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //监控当前方法执行时间
        long start = System.currentTimeMillis();
        try{
            //先执行目标方法
            return invocation.proceed();
        }finally {
            log.info("监控方法:{},执行时间：{}",invocation.getMethod(),(System.currentTimeMillis() - start));
        }
    }
}
