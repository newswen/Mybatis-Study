package com.yw.one_aop.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配接口
 *
 * @author: yuanwen
 * @since: 2024/10/21
 */
public interface MethodMatcher {

    /**
     *
     * @param method 匹配方法
     * @param targetClass 目标class
     * @return
     */
    boolean matches(Method method, Class<?> targetClass);

}
