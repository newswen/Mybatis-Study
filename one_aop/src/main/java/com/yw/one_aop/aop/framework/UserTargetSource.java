package com.yw.one_aop.aop.framework;

import org.springframework.aop.TargetSource;

/**
 * 说明
 *
 * @author: yuanwen
 * @since: 2024/10/22
 */
public class UserTargetSource implements TargetSource {

   private final Object target;

    public UserTargetSource(Object target) {
        this.target = target;
    }

    @Override
    public Class<?> getTargetClass() {
        return this.target.getClass();
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public Object getTarget() throws Exception {
        return this.target;
    }

    @Override
    public void releaseTarget(Object target) throws Exception {

    }
}
