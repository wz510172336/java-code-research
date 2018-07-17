package com.wz.design.pattern.factory.func;

import com.wz.design.pattern.factory.Mengniu;
import com.wz.design.pattern.factory.Milk;

/**
 * Created by Tom on 2018/3/4.
 */
public class MengniuFactory implements  Factory {


    @Override
    public Milk getMilk() {
        return new Mengniu();
    }
}
