package com.yw.one_aop.annation;

import java.lang.annotation.*;

/**
 * 说明
 *
 * @author: yuanwen
 * @since: 2024/10/21
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectTest {
}
