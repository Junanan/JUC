package com.june.CAS;

import java.util.concurrent.atomic.AtomicInteger;
//CAS ：比较并交换 ompareAndSet
public class CASdemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
//        参数：期望值，更新值
        atomicInteger.compareAndSet(2020,2021);
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.get());
    }
}
