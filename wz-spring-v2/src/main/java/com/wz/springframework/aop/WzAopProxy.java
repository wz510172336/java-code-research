package com.wz.springframework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @package: com.wz.springframework.aop
 * @Author:
 * @Date: 2018/5/29 0029 上午 12:04
 */
public class WzAopProxy implements InvocationHandler {
    private Object target;

    public Object getTarget() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }
}
