package com.wz.springframework.beans;


import java.lang.reflect.Method;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * @package: com.wz.springframework.beans
 * @Author:
 * @Date: 2018/5/22 0022 下午 10:43
 */
public class HandlerMapping {
    private Method method;
    private Pattern pattern;
    private Object Controllor;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;

    }





    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Object getControllor() {
        return Controllor;
    }

    public void setControllor(Object controllor) {
        Controllor = controllor;
    }



}
