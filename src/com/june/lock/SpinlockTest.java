package com.june.lock;

import java.util.concurrent.TimeUnit;

public class SpinlockTest {
    public static void main(String[] args) {
        SpinlockDemo spinlockDemo = new SpinlockDemo();

        new Thread(()->{
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlockDemo.myUnLock();
            }
        },"A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlockDemo.myUnLock();
            }
        },"B").start();

    }
}
