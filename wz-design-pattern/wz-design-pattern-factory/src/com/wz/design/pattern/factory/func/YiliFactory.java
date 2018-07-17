package com.wz.design.pattern.factory.func;

import com.wz.design.pattern.factory.Milk;
import com.wz.design.pattern.factory.Yili;

/**
 * Created by Tom on 2018/3/4.
 */
public class YiliFactory implements Factory {

    @Override
    public Milk getMilk() {
        return new Yili();
    }
}
