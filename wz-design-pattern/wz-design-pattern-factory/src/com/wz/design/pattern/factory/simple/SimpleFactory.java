package com.wz.design.pattern.factory.simple;

import com.wz.design.pattern.factory.Mengniu;
import com.wz.design.pattern.factory.Milk;
import com.wz.design.pattern.factory.Telunsu;
import com.wz.design.pattern.factory.Yili;

/**
 * Created by Tom on 2018/3/4.
 */
public class SimpleFactory {

    public Milk getMilk(String name){
        if("特仑苏".equals(name)){
            return new Telunsu();
        }else if("伊利".equals(name)){
            return new Yili();
        }else if("蒙牛".equals(name)){
            return new Mengniu();
        }else {
            System.out.println("不能生产您所需的产品");
            return null;
        }
    }

}
