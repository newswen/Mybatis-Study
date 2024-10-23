package com.yw.one_aop.aop.aspectj;

import com.yw.one_aop.aop.ClassFilter;
import com.yw.one_aop.aop.MethodMatcher;
import com.yw.one_aop.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 切面拦截
 *
 * @author: yuanwen
 * @since: 2024/10/22
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

    //定义切入点表达式支持的基本操作
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();

    //添加exection切点表达式，这个通常用于判断哪些类、方法需要包裹
    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    //PointcutExpression是由Spring AOP提供的API的一部分，用于解析和表示切点表达式。

    private final PointcutExpression pointcutExpression;

    public AspectJExpressionPointcut(String expression) {
//        PointcutParser 是一个工具类，用于解析切点表达式，并生成 PointcutExpression 对象。
//        PointcutExpression 是解析后的切点表达式的结果，它可以用来获取实际的 Pointcut 对象，用于匹配连接点。
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(
                SUPPORTED_PRIMITIVES, getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    @Override
    public boolean matches(Class<?> targetClass) {
        //这个也是源码底层的具体实现，对类的具体拦截
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        //这个也是源码底层的具体实现，对方法的具体拦截
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public ClassFilter getClassFilter() {
        //继承返回本身即可
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
