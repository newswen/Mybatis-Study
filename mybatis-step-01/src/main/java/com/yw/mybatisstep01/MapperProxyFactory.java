package com.yw.mybatisstep01;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Dao接口方法代理工厂
 *
 * @author: yuanwen
 * @since: 2024/10/23
 */
public class MapperProxyFactory<T> {

    //代理接口
    private final Class<T> mapperInterface;

    //模拟sql方法
    private final Map<String, String> sqlSessionMap;

    public MapperProxyFactory(Class<T> mapperInterface, Map<String, String> sqlSessionMap) {
        this.mapperInterface = mapperInterface;
        this.sqlSessionMap = sqlSessionMap;
    }

    public T getProxyObject() {
        MapperProxy mapperProxy = new MapperProxy(mapperInterface, sqlSessionMap);
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }

}
