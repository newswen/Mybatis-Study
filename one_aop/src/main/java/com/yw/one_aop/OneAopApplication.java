package com.yw.one_aop;

import com.yw.one_aop.service.IUserService;
import com.yw.one_aop.service.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;

@SpringBootApplication
@EnableAspectJAutoProxy
public class OneAopApplication {

    @Resource
    private IUserService userService;

    public static void main(String[] args) {
//        SpringApplication.run(OneAopApplication.class, args);
        ApplicationContext context = SpringApplication.run(OneAopApplication.class, args);

        // 从Spring容器中获取IUserService的实例
        IUserService userService = context.getBean(UserServiceImpl.class);

        // 调用方法并打印结果
        System.out.println(userService.queryUserInfo());

    }

}
