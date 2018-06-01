package com.wz.springframework.annotation;

import java.lang.annotation.*;

/**
 * @param
 * @package: com.wz.spring.service.annotation
 * @Author:
 * @Description:
 * @Date: 2018/4/29 0029 下午 7:29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WzControllor {

}
