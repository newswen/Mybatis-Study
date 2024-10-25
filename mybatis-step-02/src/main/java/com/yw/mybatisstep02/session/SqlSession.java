package com.yw.mybatisstep02.session;

/**
 * sqlSession 接口，包含执行 sql 的方法，获取映射器代理对象
 *
 * @author: yuanwen
 * @since: 2024/10/25
 */
public interface SqlSession {

    <T> T selectOne(String statement, Object[] args);

    //<T>
    <T> T getMapper(Class<T> type);
}
