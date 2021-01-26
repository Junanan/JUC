package com.june.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

//ABA 类似乐观锁（对未修改的数据加锁）
public class ABAproblem {
    //初始值不能设置太大 只能在-127-128之间
   static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);
    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println(atomicReference.compareAndSet(100, 101));
            System.out.println(atomicReference.compareAndSet(101, 100));
        }, "T1").start();

        new Thread(() -> {
            //保证上面线程先执行
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2020));
            System.out.println(atomicReference.get());
        }).start();
    }
}
//在多线程环境中，某个location（或可以理解为某内存地址指向的变量）
// 会被一个线程连续重复读取两次，那么只要第一次读取的值和第二次读取的值一样，
// 那么这个线程就会认为这个变量在两次读取时间间隔内没有发生任何变化；