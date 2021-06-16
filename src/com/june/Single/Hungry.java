package com.june.Single;
//饿汉式单例
public class Hungry {
    private Hungry(){

    }
    private static final Hungry hungry =new Hungry();

    public static Hungry getInstance(){
        return hungry;
    }
}
