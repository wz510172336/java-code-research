package com.wz.springframework.context;

import com.wz.springframework.annotation.WzAutowired;
import com.wz.springframework.annotation.WzControllor;
import com.wz.springframework.annotation.WzReposity;
import com.wz.springframework.annotation.WzService;
import com.wz.springframework.aop.WzAopConfig;
import com.wz.springframework.beans.BeanDefination;
import com.wz.springframework.beans.BeanPostPocessor;
import com.wz.springframework.beans.BeanWrapper;
import com.wz.springframework.core.BeanFactory;
import com.wz.springframework.support.AopReader;
import com.wz.springframework.support.BaseReader;
import com.wz.springframework.support.BeanDefinationReader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @package: com.wz.springframework.context
 * @Author:
 * @Date: 2018/5/1 0001 上午 10:32
 */
public class WzApplicationContext implements BeanFactory {
    private String[] configLocations;
    private BeanDefinationReader beanDefinationreader;
    private AopReader aopReader;
    //BeanDefinition 容器
    private Map<String, BeanDefination> BeanDefinitionMapping = new ConcurrentHashMap<String, BeanDefination>();
    //Bean 容器
    private Map<String, Object> BeanCacheMap = new ConcurrentHashMap<String, Object>();

    //beanName列表
    private List<String> beanFactoryNames=new ArrayList<>();

    // BeanWrapper容器
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<String, BeanWrapper>();
    
      //AOP 配置类
            WzAopConfig wzAopConfig=new WzAopConfig();
    //完成容器初始化
    public WzApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }


    public void refresh() {

        //定位
        beanDefinationreader = new BeanDefinationReader(configLocations[0]);
         aopReader=new AopReader(configLocations[0]);
        //加载

        List<String> classnames =beanDefinationreader.loadBeanDefinitions();

        //注册
        doRegister(classnames);

        //如果！isLazyInit,需要调用getBean，进行依赖注入,全部注入到容器
        doAutoWired();


    }

    private void doAutoWired() {

        for (Entry<String, BeanDefination> definationEntry : BeanDefinitionMapping.entrySet()) {
            String className = definationEntry.getKey();
            BeanDefination beanDefinition = definationEntry.getValue();
            if (!beanDefinition.isLazyInit()) {
                getBean(className);
            }
        }

    }
//赋值
    private void poplarBean(String beanName, Object instance)throws Exception {
        Class<?> clazz = instance.getClass();
        if (!(clazz.isAnnotationPresent(WzControllor.class) ||
                clazz.isAnnotationPresent(WzReposity.class) ||
                clazz.isAnnotationPresent(WzService.class))) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (!f.isAnnotationPresent(WzAutowired.class)) {
                continue;
            }

            WzAutowired wzAutowired = f.getAnnotation(WzAutowired.class);
            String name = wzAutowired.value().trim();
            if ("".equals(name)) {
                f.setAccessible(true);
                name=f.getType().getSimpleName();
                char[] fn=name.toCharArray();
                 fn[0]+=32;
                 String factoryName=String.valueOf(fn);

                if(!beanWrapperMap.containsKey(factoryName)){
                  //循环依赖
                    getBean(factoryName);
                }
                try {
                    f.set(instance,beanWrapperMap.get(factoryName).getWraprerInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
           // f.set(instance,beanWrapperMap.get(name).getWraprerInstance());
        }


    }

    //注册具体过程
    private void doRegister(List<String> beanclassnames) {

        if (null != beanclassnames) {
            for (String classname : beanclassnames) {
                Class<?> clazz = null;
                try {
                    clazz = Class.forName(classname);
                    BeanDefination beanDefination = beanDefinationreader.registerBean(classname);
                    if (clazz.isInterface()) {
                        //按接口注册
                        Class<?>[] interfaces = clazz.getInterfaces();
                        for (Class<?> clayy : interfaces) {
                            BeanDefinitionMapping.put(clayy.getName(), beanDefination);
                            System.out.println(clayy.getName());
                        }
                    }
                    //非接口注入
                    BeanDefinitionMapping.put(beanDefination.getFactoryname(), beanDefination);
                    beanFactoryNames.add(beanDefination.getFactoryname());

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public Object getBean(String factoryName) {
        BeanDefination beanDefination = BeanDefinitionMapping.get(factoryName);
        if (null != beanDefination) {
            //生成通知事件
            BeanPostPocessor beanPostPocessor = new BeanPostPocessor();
            Object object = instant(beanDefination);
            beanPostPocessor.postProcessBeforeInitialization(object, factoryName);
            BeanWrapper beanWrapper = new BeanWrapper(object);
            beanWrapper.setBeanPostPocessor(beanPostPocessor);
            beanWrapperMap.put(factoryName, beanWrapper);
            beanPostPocessor.postProcessAfterInitialization(object, factoryName);
            try {

                poplarBean(factoryName,object);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return beanWrapperMap.get(factoryName).getWraprerInstance();

        }
        return null;
    }

    public String[] getConfigLocations() {
        return configLocations;
    }

    public Object instant(BeanDefination beanDefination) {
        String classname = beanDefination.getClassname();
        Object instance = null;
        if (BeanCacheMap.containsKey(classname)) {
            instance = BeanCacheMap.get(classname);
        } else {
            try {
                instance = Class.forName(classname).newInstance();
                BeanCacheMap.put(classname, instance);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

        return instance;

    }
    public Object instantAop(BeanDefination beanDefination){
        String className=beanDefination.getClassname();
         Method[] methodss=null;
        Object aspect[]=null;
        Object instance=null;
        Pattern pattern=null;
        try {
        List<String[]> list=aopReader.getAoplist();
             for(int i=0;i<list.size();i++) {
                 String[] strings=list.get(i);
                 if (strings[0] == "expression") {
                    pattern = Pattern.compile(strings[1]);
                 } else {
                     if (strings[0].contains("aspect")) {
                        String classAspectName = strings[1].substring(0, strings[1].lastIndexOf(".")).trim();
                         Class<?> aClass= Class.forName(classAspectName);
                        String methodName= strings[1].substring(strings[1].lastIndexOf(".")+1).trim();
                             methodss[i]=aClass.getMethod(methodName);
                             aspect[i]=aClass.newInstance();
                     }
                 }
             }
            instance=Class.forName(className);
            Method[] methods=instance.getClass().getDeclaredMethods();
               for(Method method:methods){
                   Matcher matcher=pattern.matcher(method.toString());
                   if(matcher.matches()){
                       wzAopConfig.put(method,aspect[0],methodss);
                   }
               }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return wzAopConfig;
    }

    public List<String> getBeanFactoryNames() {
        return beanFactoryNames;
    }

    public Map<String, BeanWrapper> getBeanWrapperMap() {
        return beanWrapperMap;
    }
}
