package com.june.Lock8;

import java.util.concurrent.TimeUnit;

//4、两部手机、请问先打印邮件还是短信？
//5、两个静态同步方法，同一部手机，请问先打印邮件还是短信？
// 6、两个静态同步方法，2部手机，请问先打印邮件还是短信？

//4.被synchronized修饰的方法，锁的对象是方法的调用者。因为用了两个对象调用各自的方法，
// 所以两个方法的调用者不是同一个，所以两个方法用的不是同一个锁，后调用的方法不需要等待先调用的方法

//5.锁的对象是类的class对象。因为两个同步方法都被static修 饰了，所以两个方法用的是同一个锁，
// 后调用的方法需要等待先调用的方法。

//6.锁的对象是类的class对象。因为两个同步方法都被static修 饰了，
// 即便用了两个不同的对象调用方法，两个方法用的还是同一个锁，后调用的方法需要等待先调用的方法。
public class Lock82 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }


    static class Phone {
        public synchronized static void sendEmail() throws Exception {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sendEmail");
        }

        public synchronized static void sendSMS() throws Exception {
            System.out.println("sendSMS");
        }
    }
}