package com.wz.springframework.annotation;

import java.lang.annotation.*;

/**
 * @package: com.wz.spring.service.annotation
 * @Author:
 * @Date: 2018/4/29 0029 下午 7:48
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WzReposity {
}
