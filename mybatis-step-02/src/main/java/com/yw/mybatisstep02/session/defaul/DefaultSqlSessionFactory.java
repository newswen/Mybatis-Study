package com.yw.mybatisstep02.session.defaul;

import com.yw.mybatisstep02.binding.MapperRegister;
import com.yw.mybatisstep02.session.SqlSession;
import com.yw.mybatisstep02.session.SqlSessionFactory;

/**
 * 说明
 *
 * @author: yuanwen
 * @since: 2024/10/25
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegister mapperRegister;

    public DefaultSqlSessionFactory(MapperRegister mapperRegister) {
        this.mapperRegister = mapperRegister;
    }

    //对外代理工厂，创建sqlSession
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegister);
    }
}
