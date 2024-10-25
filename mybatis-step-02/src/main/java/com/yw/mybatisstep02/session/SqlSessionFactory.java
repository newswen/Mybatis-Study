package com.yw.mybatisstep02.session;

/**
 * sqlSession工厂,创建sqlSession
 *
 * @author: yuanwen
 * @since: 2024/10/25
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
