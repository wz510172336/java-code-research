package com.wz.springframework.support;

import com.wz.springframework.beans.BeanDefination;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @package: com.wz.springframework.support
 * @Author:
 * @Date: 2018/5/1 0001 上午 11:05
 */
public class BeanDefinationReader extends BaseReader {


    private List<String> beanName = new ArrayList<String>();
    private static final String PACKAGE = "ScanPackage";

    public BeanDefinationReader(String locations) {
        super(locations);
        doscan(super.pros.getProperty(PACKAGE));
    }


    private void doscan(String packagename) {
        URL url = this.getClass().getClassLoader().getResource("/" + packagename.replaceAll("\\.", "/"));

        File dir = new File(url.getFile());
     //   System.out.println(url+"------"+url.getFile());
        for (File f1 : dir.listFiles()) {
            if (f1.isDirectory()) {
                this.doscan(packagename + "." + f1.getName());

            } else {
                ;
                beanName.add(packagename + "." + f1.getName().replace(".class", ""));
            }

        }


    }

    public BeanDefination registerBean(String classname) {
      if(beanName.contains(classname)){
          BeanDefination beanDefination=new BeanDefination();
         beanDefination.setClassname(classname);
         beanDefination.setFactoryname(LowerCase(classname.substring(classname.lastIndexOf(".")+1)));
         return  beanDefination;
      }
      return null;
    }


   public  List<String> loadBeanDefinitions() {
        return this.beanName;
    }
    public String LowerCase(String name) {
        char[] cha = name.toCharArray();
        cha[0] += 32;
        return String.valueOf(cha);

    }



;


}
