package com.yw.mybatisstep02.binding;

import cn.hutool.core.lang.ClassScanner;
import com.yw.mybatisstep02.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Mapper注册器，扫描对应包的Mapper接口，并生成代理对象放入到Map中
 *
 * @author: yuanwen
 * @since: 2024/10/25
 */
public class MapperRegister {

    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory) this.knownMappers.get(type);
        if (mapperProxyFactory == null) {
            //避免重复创建
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        } else {
            try {
                return mapperProxyFactory.newInstan(sqlSession);
            } catch (Exception var5) {
                Exception e = var5;
                throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
            }
        }
    }

    //添加Mapper接口到Mapper注册器中
    public void addMapper(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        mapperSet.forEach(this::addMappers);
    }

    public void addMappers(Class<?> type) {
        //先判断扫描的包中的类是不是都是接口，才能在后续代理工厂中生成映射器，避免报错类不是接口
        if (type.isInterface()) {
            if (this.hasMapper(type)) {
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }

        }
        knownMappers.put(type, new MapperProxyFactory<>(type));
    }


    public <T> boolean hasMapper(Class<T> type) {
        return this.knownMappers.containsKey(type);
    }


}
