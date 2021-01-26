package com.june.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Lock

/**
 * 可重入锁（也叫递归锁）
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 */
public class Demo2 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSMS();
        }, "A").start();

        new Thread(() -> {
            phone.sendSMS();
        }, "B").start();
    }

}

class Phone2 {
    Lock lock = new ReentrantLock();
    public void sendSMS() {
        lock.lock();
        // lock.lock(); 锁必须匹配，如果两个锁，只有一个解锁就会失败
        try {
            System.out.println(Thread.currentThread().getName() + " sendSMS");
            sendEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            // lock.lock();
        }
    }

    public void sendEmail() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " sendEmail");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
