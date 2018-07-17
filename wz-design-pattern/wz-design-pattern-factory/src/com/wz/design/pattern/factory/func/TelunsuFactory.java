package com.wz.design.pattern.factory.func;

import com.wz.design.pattern.factory.Milk;
import com.wz.design.pattern.factory.Telunsu;

/**
 * 事情变得越来越专业
 * Created by Tom on 2018/3/4.
 */
public class TelunsuFactory implements Factory {

    @Override
    public Milk getMilk() {
        return new Telunsu();
    }
}
