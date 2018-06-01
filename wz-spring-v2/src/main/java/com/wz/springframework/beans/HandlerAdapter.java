package com.wz.springframework.beans;

import sun.security.krb5.internal.PAData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

/**
 * @package: com.wz.springframework.beans
 * @Author:
 * @Date: 2018/5/26 0026 上午 12:59
 */
//动态参数处理器
public class HandlerAdapter {


    private Map<String, Integer> map;

    public HandlerAdapter(Map<String, Integer> map) {
        this.map = map;
    }


    public WzModelandView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handlerMapping) {
        Class<?>[] clayy = handlerMapping.getMethod().getParameterTypes();
        Map<String, String[]> pMap = req.getParameterMap();
        Object[] paramvalues = new Object[clayy.length];
        if (this.map.containsKey("HttpServletRequest")) {
            int reIndex = this.map.get(HttpServletRequest.class.getSimpleName());
            paramvalues[reIndex] = req;
        }
        if (this.map.containsKey("HttpServletResponse")) {
            int rqIndex = this.map.get(HttpServletResponse.class.getSimpleName());
            paramvalues[rqIndex] = resp;
        }
        pMap.entrySet().stream().forEach(entry -> {
            String value = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", "");
            if (this.map.containsKey(entry.getKey())) {
                Integer index = map.get(entry.getKey());
                paramvalues[index] = caseType(value, clayy[index]);
            }

        });
        try {
            if (paramvalues == null) {
                return null;
            }
            //拿到实参数组后反射调用
            Object object = handlerMapping.getMethod().invoke(handlerMapping.getControllor(), paramvalues);
            boolean isWzModelAndView = object.getClass() == WzModelandView.class;
            if (isWzModelAndView) {
                return (WzModelandView) object;
            } else {
                return null;
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;

    }

    private Object caseType(String value, Class<?> aClass) {
        if (aClass == String.class) {
            return value;
        } else if (aClass == Integer.class) {
            return Integer.valueOf(value);
        } else if (aClass == int.class) {
            return Integer.valueOf(value).intValue();
        } else {
            return null;
        }
    }


}
