package com.june.Single;

public class Lazy {
    private Lazy(){
        System.out.println(Thread.currentThread().getName()+">>>");
    }
//    private static  Lazy LAZY ;
    //避免指令重排 防止其他线程判断为空
    private volatile static Lazy LAZY ;
    //单线程可行
//    public static Lazy getInstance(){
//                if (LAZY == null){
//                    new Lazy();
//                }
//        }
//        return LAZY;
//    }
    // 双重锁验证  ---> DCL 懒汉式 单例模式
    public static Lazy getInstance(){
        if (LAZY == null){
            synchronized (Lazy.class){
                if (LAZY == null){
                    new Lazy();
                }
            }
        }
        return LAZY;
    }

    public static void main(String[] args) {
        for (int i = 0; i <5 ; i++) {
            new Thread(() -> {
                Lazy.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
