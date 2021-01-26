package com.june.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
//使用AtomicStampedReference  （带版号）
public class ABAsolve {
    static AtomicStampedReference<Integer> atomicStampedReference =
            new AtomicStampedReference<Integer>(100,1);
    public static void main(String[] args) {
        int stamp  = atomicStampedReference.getStamp();
        System.out.println("T1 stamp1--->"+stamp);
        new Thread(()->{
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println("T1 stamp2--->"+atomicStampedReference.getStamp());
            //休眠 让下面线程拿到版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println("T1 stamp3--->"+atomicStampedReference.getStamp());
        },"T1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100, 2020, stamp, stamp + 1));
            System.out.println("T2 stamp--->"+atomicStampedReference.getStamp());
            System.out.println("T2 value-->"+atomicStampedReference.getReference());
        },"T2").start();
    }
}
