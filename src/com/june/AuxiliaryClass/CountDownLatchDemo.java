package com.june.AuxiliaryClass;
//计数器

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //总数是6  必须要执行完成
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"->"+"go");
                countDownLatch.countDown(); // -1
            },String.valueOf(i)).start();
        }
//        countDownLatch.await(); //等待计数器归零，然后在向下执行
        System.out.println("close");
    }
}
