package com.yw.one_aop.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.TargetSource;

/**
 * 包装切面通知信息
 *
 * @author: yuanwen
 * @since: 2024/10/22
 */
public class AdviceSupport {

    //默认为JDK动态代理
    private boolean proxyType = false;

    //需要代理的对象
    private TargetSource targetSource;

    //方法拦截
    private MethodInterceptor methodInterceptor;

    //包装的方法匹配
    private MethodMatcher methodMatcher;

    public boolean isProxyType() {
        return proxyType;
    }

    public void setProxyType(boolean proxyType) {
        this.proxyType = proxyType;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
