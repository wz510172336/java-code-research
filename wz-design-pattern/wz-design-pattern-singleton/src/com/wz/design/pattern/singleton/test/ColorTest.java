package com.wz.design.pattern.singleton.test;

import com.wz.design.pattern.singleton.lazy.LazyOne;
import com.wz.design.pattern.singleton.lazy.LazyTwo;
import com.wz.design.pattern.singleton.register.Color;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Tom on 2018/3/7.
 */
public class ColorTest {
    public static void main(String[] args) {
        System.out.println(Color.RED);
    }
}