package com.yw.one_aop.aop.framework;

import com.yw.one_aop.aop.AdviceSupport;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 说明
 *
 * @author: yuanwen
 * @since: 2024/10/22
 */
public class CGLibProxy implements AopProxy, MethodInterceptor {

    private final AdviceSupport adviceSupport;

    public CGLibProxy(AdviceSupport adviceSupport) {
        this.adviceSupport = adviceSupport;
    }

    @Override
    public Object getProxy() {
        // 使用 CGLib 创建代理对象
        Enhancer enhancer = new Enhancer();
        try {
            enhancer.setSuperclass(Objects.requireNonNull(adviceSupport.getTargetSource().getTarget()).getClass());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        enhancer.setCallback(this);
        return enhancer.create();
    }

    //这个就是CGLib代理的拦截方法，setCallback里面的方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
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
