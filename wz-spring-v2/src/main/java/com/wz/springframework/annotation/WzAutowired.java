package com.wz.springframework.annotation;

import java.lang.annotation.*;

/**
 * @param
 * @package: com.wz.spring.service.annotation
 * @Author:
 * @Description:
 * @Date: 2018/4/29 0029 下午 7:36
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WzAutowired {
    String value() default "";
}
