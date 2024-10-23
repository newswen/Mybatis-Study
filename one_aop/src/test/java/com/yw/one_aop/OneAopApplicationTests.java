package com.yw.one_aop;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.yw.one_aop.aop.AdviceSupport;
import com.yw.one_aop.aop.aspectj.AspectJExpressionPointcut;
import com.yw.one_aop.aop.framework.AopProxy;
import com.yw.one_aop.aop.framework.ProxyFactory;
import com.yw.one_aop.aop.framework.UserMethodInterceptor;
import com.yw.one_aop.aop.framework.UserTargetSource;
import com.yw.one_aop.service.CgLibServiceImpl;
import com.yw.one_aop.service.IUserService;
import com.yw.one_aop.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
@Slf4j
class OneAopApplicationTests {


    @Test
    void testJdkDynamicProxy() {
        IUserService userService = new UserServiceImpl();
        //Spring的cglib动态代理
        IUserService userServiceProxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserService.class},
                (proxy, method, args) -> {
                    //获取代理对象的方法名称
                    String methodName = method.getName();
                    if ("queryUserInfo".equals(methodName)) {
                        String result = "你被代理了!" + method.invoke(userService, args);
                        return result;
                    } else {
                        return method.invoke(userService, args);
                    }
                });
        //调用代理对象的方法
        String result = userServiceProxy.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    void testCglibDynamicProxy() {
        CgLibServiceImpl target = new CgLibServiceImpl();
        // 使用 CGLib 创建代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CgLibServiceImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                // 获取代理对象的方法名称
                String methodName = method.getName();
                System.out.println("调用方法：" + methodName);
                if ("queryUserInfo9".equals(methodName)) {
                    // 调用原始对象的方法
                    String result = "你被代理了!" + method.invoke(target, args);
                    return result;
                } else {
                    // 对于其他方法，也可以添加处理逻辑
                    return method.invoke(target, args);
                }
            }
        });

        // 创建代理对象
        CgLibServiceImpl cgLibService = (CgLibServiceImpl) enhancer.create();

        // 调用代理对象的方法
        String result = cgLibService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_spring_aop() {
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.queryUserInfo());
    }

    @Test
    public void test_aop_match() throws NoSuchMethodException {
        AspectJExpressionPointcut aspectJExpressionPointcut =
                new AspectJExpressionPointcut("execution(* com.yw.one_aop.service..*(..))");
        Class<UserServiceImpl> clazz = UserServiceImpl.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(aspectJExpressionPointcut.matches(clazz));
        System.out.println(aspectJExpressionPointcut.matches(method, clazz));

    }

    @Test
    public void test_aop_jdk() {
        IUserService userService = new UserServiceImpl();

        //1.创建包装切面通知信息，填充目标对象、方法拦截、方法匹配
        AdviceSupport adviceSupport = new AdviceSupport();
        //设置代理类型:false-JDK代理，true-CGLIB代理
        adviceSupport.setProxyType(false);
        adviceSupport.setTargetSource(new UserTargetSource(userService));
        adviceSupport.setMethodInterceptor(new UserMethodInterceptor());
        adviceSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.yw.one_aop.service..*(..))"));

        ProxyFactory proxyFactory = new ProxyFactory(adviceSupport);
        AopProxy aopProxy = proxyFactory.getProxy();

        IUserService proxy = (IUserService) aopProxy.getProxy();
        log.info(proxy.queryUserInfo());
    }

    @Test
    public void test_aop_cglib() {

        CgLibServiceImpl cgLibService = new CgLibServiceImpl();


        //1.创建包装切面通知信息，填充目标对象、方法拦截、方法匹配
        AdviceSupport adviceSupport = new AdviceSupport();
        //设置代理类型:false-JDK代理，true-CGLIB代理
        adviceSupport.setProxyType(true);
        adviceSupport.setTargetSource(new UserTargetSource(cgLibService));
        //具体的切面拦截方法
        adviceSupport.setMethodInterceptor(new UserMethodInterceptor());
        adviceSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.yw.one_aop.service..*(..))"));

        ProxyFactory proxyFactory = new ProxyFactory(adviceSupport);
        AopProxy aopProxy = proxyFactory.getProxy();

        CgLibServiceImpl proxy = (CgLibServiceImpl) aopProxy.getProxy();
        log.info(proxy.queryUserInfo());

    }

}
