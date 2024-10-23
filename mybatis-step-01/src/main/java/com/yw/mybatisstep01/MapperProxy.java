package com.yw.mybatisstep01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Dao接口使用JDK动态代理进行方法调用，可以思考为啥每次定义Dao接口，本质是JDK基于实现接口的动态代理
 *
 * @author: yuanwen
 * @since: 2024/10/23
 */
public class MapperProxy<T> implements InvocationHandler {

    //代理接口
    private final Class<T> mapperInterface;

    //模拟sql方法
    private final Map<String, String> sqlSessionMap;

    public MapperProxy(Class<T> mapperInterface, Map<String, String> sqlSessionMap) {
        this.mapperInterface = mapperInterface;
        this.sqlSessionMap = sqlSessionMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行代理方法，拦截
        System.out.println("执行代理方法，拦截"+mapperInterface.getName());
        return sqlSessionMap.get(mapperInterface.getName().concat(".").concat(method.getName()));
    }
}
