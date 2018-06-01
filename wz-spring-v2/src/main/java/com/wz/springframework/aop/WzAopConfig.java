package com.wz.springframework.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @package: com.wz.springframework.aop
 * @Author:
 * @Date: 2018/5/29 0029 上午 12:08
 */
public class WzAopConfig {

    private  Map<Method,Aspect> aspectMap=new HashMap<Method,Aspect>();



   public void put(Method target,Object aspect,Method[] methods){
       aspectMap.put(target,new WzAopConfig.Aspect(aspect,methods));
   }



    public Aspect get(Method method){
      return aspectMap.get(method);
   }

   public boolean containsKey(Method method){
       return  this.aspectMap.containsKey(method);
   }

    public class Aspect {
       private Object aspect;
       private Method[] points;

        public Aspect(Object aspect, Method... points) {
            this.aspect = aspect;
            this.points = points;
        }

        public Object getAspect() {
            return aspect;
        }

        public void setAspect(Object aspect) {
            this.aspect = aspect;
        }

        public Method[] getPoints() {
            return points;
        }

        public void setPoints(Method... points) {
            this.points = points;
        }
    }
}
