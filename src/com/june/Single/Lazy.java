package com.june.Single;

import java.util.LinkedHashMap;

public class Lazy {
    private Lazy(){
        System.out.println(Thread.currentThread().getName()+">>>");
    }
    //避免指令重排 防止其他线程判断为空
    private volatile static Lazy LAZY ;
    // 双重锁验证  ---> DCL 懒汉式 单例模式
    public static Lazy getInstance(){
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (LAZY == null){
            //给对象实例上锁
            synchronized (Lazy.class){
                if (LAZY == null){
                    LAZY =  new Lazy();
                }
            }
        }
        return LAZY;
    }

    public static void main(String[] args) {
        for (int i = 0; i <5 ; i++) {
            new Thread(() -> {
                System.out.println(Lazy.getInstance());
            },String.valueOf(i)).start();
        }
    }
}
