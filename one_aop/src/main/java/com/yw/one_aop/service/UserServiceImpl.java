package com.yw.one_aop.service;

import com.yw.one_aop.annation.AspectTest;
import org.springframework.stereotype.Service;

/**
 * 说明
 *
 * @author: yuanwen
 * @since: 2024/10/17
 */
@Service
public class UserServiceImpl implements IUserService {

    @AspectTest
    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "yw测试";
    }
}
