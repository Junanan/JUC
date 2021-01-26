package com.june.jvm;

import java.util.LinkedList;
import java.util.List;

public class ClassLoader {
    public static void main(String[] args) {
        Object object = new Object();
        ClassLoader demo01 = new ClassLoader();
        System.out.println(object.getClass().getClassLoader());
        System.out.println(demo01.getClass().getClassLoader());
        System.out.println(demo01.getClass().getClassLoader().getParent());
        System.out.println(demo01.getClass().getClassLoader().getParent().getParent());
        /* 结果：
        null
        sun.misc.Launcher$AppClassLoader@18b4aac2
        sun.misc.Launcher$ExtClassLoader@1b6d3586
        null
        **/
        List<int[]> list = new LinkedList<>();
    }
}
