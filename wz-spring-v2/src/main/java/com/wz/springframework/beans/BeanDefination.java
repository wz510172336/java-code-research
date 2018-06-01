package com.wz.springframework.beans;

import lombok.Data;

/**
 * @package: com.wz.springframework.beans
 * @Author:
 * @Date: 2018/5/1 0001 上午 11:04
 */
@Data
public class BeanDefination {
    private String classname;
    private String factoryname;
    private boolean lazyInit=false;
}
