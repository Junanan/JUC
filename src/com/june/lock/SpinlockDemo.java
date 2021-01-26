package com.june.lock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinlockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<Thread>();
    //加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"==>拿到锁了");

        while(!atomicReference.compareAndSet(null,thread)){

        }
    }
    //解锁
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"==>解锁了");
        atomicReference.compareAndSet(thread,null);
    }
}
