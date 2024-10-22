package com.yw.one_aop.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * MethodInvocation 提供了 proceed() 方法来继续方法的调用链。
 *
 * @author: yuanwen
 * @since: 2024/10/22
 */
public class ReflactMethodInvocation implements MethodInvocation {

    //目标对象
    private final Object target;

    //目标方法
    private final Method method;

    //目标参数
    private final Object[] args;

    public ReflactMethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
