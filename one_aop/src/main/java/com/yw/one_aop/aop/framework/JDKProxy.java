package com.yw.one_aop.aop.framework;

import com.yw.one_aop.aop.AdviceSupport;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * JDK代理类
 *
 * @author: yuanwen
 * @since: 2024/10/22
 */
public class JDKProxy implements AopProxy, InvocationHandler {

    //注入包装切面通知类
    private final AdviceSupport adviceSupport;

    public JDKProxy(AdviceSupport adviceSupport) {
        this.adviceSupport = adviceSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                Objects.requireNonNull(adviceSupport.getTargetSource().getTargetClass()).getInterfaces(),
                this);
    }


    //这里也可以直接写在this中的，分离出来，实现InvocationHandler接口替换函数式编程写法
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        //这里
        //方法匹配
        if (adviceSupport.getMethodMatcher().matches(method, adviceSupport.getTargetSource().getTargetClass())) {
            //小傅哥的是直接将这部分的解耦开了
            //其实可以直接在这里面加入额外的切面方法
            //方法拦截执行切面逻辑
            adviceSupport.getMethodInterceptor().invoke(new ReflactMethodInvocation(adviceSupport.getTargetSource().getTarget(), method, objects));
        }
        //执行目标方法
        return method.invoke(adviceSupport.getTargetSource().getTarget(), objects);
    }
}
