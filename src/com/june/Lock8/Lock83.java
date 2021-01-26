package com.june.Lock8;

import java.util.concurrent.TimeUnit;

//7、一个普通同步方法，一个静态同步方法，同一部手机，请问先打印邮件还是短信？
//7.被synchronized和static修饰的方法，锁的对象是类的class对象。仅仅被synchronized修饰的方法，
// 锁的对象是方法的调用者。因为两个方法锁的对象不是同一个，所以两个方法用的不是同一个锁，
//后调用的方法不需要等待先调用的方法。
//8、一个普通同步方法，一个静态同步方法，2部手机，请问先打印邮件还是短信？
//被synchronized和static修饰的方法，锁的对象是类的class对象。
// 仅仅被synchronized修饰的方 法，锁的对象是方法的调用者。即便是用同一个对象调用两个方法，
// 锁的对象也不是同一个，所以两个方法用的不是同一个锁，后调用的方法不需要等待先调用的方法
public class Lock83 {
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

        public synchronized  void sendSMS() throws Exception {
            System.out.println("sendSMS");
        }
    }
}