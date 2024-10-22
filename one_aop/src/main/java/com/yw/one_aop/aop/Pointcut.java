package com.yw.one_aop.aop;

/**
 * 切入点
 *
 * @author: yuanwen
 * @since: 2024/10/21
 */
public interface Pointcut {

    /**
     * 获取类过滤
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * 获取方法匹配
     * @return
     */
    MethodMatcher getMethodMatcher();

}
