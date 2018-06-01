package com.wz.springframework.beans;

/**
 * @package: com.wz.springframework.beans
 * @Author:
 * @Date: 2018/5/19 0019 下午 10:14
 */
public class BeanPostPocessor {

    //为在Bean的初始化前提供回调入口
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }


    //为在Bean的初始化之后提供回调入口
   public  Object postProcessAfterInitialization(Object bean, String beanName){return  bean;}


}
