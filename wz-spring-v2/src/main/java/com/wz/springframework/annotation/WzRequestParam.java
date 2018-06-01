package com.wz.springframework.annotation;

import java.lang.annotation.*;

/**
 * @param
 * @package: com.wz.spring.service.annotation
 * @Author:
 * @Description:
 * @Date: 2018/4/29 0029 下午 7:32
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WzRequestParam {
    String value() default "";
    boolean required() default true;
    String defaultValue() default "";
}
