package com.june.Single;
//饿汉式单例
public class Hungry {
    // 一上来就创建  造成内存浪费
    private byte[] bety1 = new byte[1024*1024];
    private byte[] bety2 = new byte[1024*1024];
    private byte[] bety3 = new byte[1024*1024];
    private byte[] bety4 = new byte[1024*1024];
    private Hungry(){

    }
    private static final Hungry HUNGRY =new Hungry();

    public static Hungry getInstance(){
        return HUNGRY;
    }
}
