package com.wz.design.pattern.factory.func;

import com.wz.design.pattern.factory.Milk;
import com.wz.design.pattern.factory.Sanlu;

/**
 * Created by Tom on 2018/3/4.
 */
public class SanluFactory implements  Factory {
    @Override
    public Milk getMilk() {
        return new Sanlu();
    }
}
