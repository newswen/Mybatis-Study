package com.yw.mybatisstep02.binding;

import com.yw.mybatisstep02.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 映射器代理-JDK动态代理
 *
 * @author: yuanwen
 * @since: 2024/10/25
 */
public class MapperProxy<T> implements InvocationHandler {

    private final Class<T> mapperInterface;

    private final SqlSession sqlSession;

    public MapperProxy(Class<T> mapperInterface, SqlSession sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return sqlSession.selectOne(method.getName(), args);
    }
}
