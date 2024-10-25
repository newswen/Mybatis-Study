package com.yw.mybatisstep02.session.defaul;

import com.yw.mybatisstep02.binding.MapperRegister;
import com.yw.mybatisstep02.session.SqlSession;

import java.util.Arrays;

/**
 * 具体的sqlSession实现类
 *
 * @author: yuanwen
 * @since: 2024/10/25
 */
public class DefaultSqlSession implements SqlSession {

    private final MapperRegister mapperRegister;

    public DefaultSqlSession(MapperRegister mapperRegister) {
        this.mapperRegister = mapperRegister;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T selectOne(String statement, Object[] args) {
        return (T) ("你被代理了！方法：" + statement + " 入参:" + Arrays.toString(args));
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegister.getMapper(type, this);
    }
}
