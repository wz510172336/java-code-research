package com.wz.springframework.beans;

/**
 * @package: com.wz.springframework.beans
 * @Author:
 * @Date: 2018/5/1 0001 上午 11:04
 */
public class BeanWrapper {
    private  BeanPostPocessor beanPostPocessor;
    private  Object targetInstance;
    private Object wraprerInstance;

    public BeanPostPocessor getBeanPostPocessor() {
        return beanPostPocessor;
    }

    public void setBeanPostPocessor(BeanPostPocessor beanPostPocessor) {
        this.beanPostPocessor = beanPostPocessor;
    }

    public BeanWrapper(Object targetInstance) {
        this.targetInstance = targetInstance;
        this.wraprerInstance = targetInstance;
    }

    public Object getWraprerInstance() {
        return wraprerInstance;
    }

    public Class<?> getWrapperClass(){
        return wraprerInstance.getClass();

    }
}
