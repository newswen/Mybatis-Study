package com.yw.mybatisstep02.binding;

import com.yw.mybatisstep02.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * 映射器代理工厂
 *
 * @author: yuanwen
 * @since: 2024/10/25
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @SuppressWarnings("unchecked")
    public T newInstan(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface, sqlSession);
        return (T) Proxy.newProxyInstance(this.mapperInterface.getClassLoader(),
                new Class[]{this.mapperInterface},
                mapperProxy);
    }
}
