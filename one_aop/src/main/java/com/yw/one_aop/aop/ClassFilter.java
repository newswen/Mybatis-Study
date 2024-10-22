package com.yw.one_aop.aop;

/**
 * 类过滤接口
 *
 * @author: yuanwen
 * @since: 2024/10/21
 */
public interface ClassFilter {

    /**
     * ClassFilter是用来过滤类的工具，它允许你在定义pointcut时指定哪些类应该受到切面的影响。
     * 通过实现matches方法，来判断给定的类是否应该被匹配。
     * @param clazz 类
     * @return
     */
    boolean matches(Class<?> clazz);
}
