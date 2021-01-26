package com.june.lock;
//synchronize

/**
 * 可重入锁（也叫递归锁）
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 */
public class Demo1 {
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

class Phone {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + " sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + " sendEmail");
    }
}

