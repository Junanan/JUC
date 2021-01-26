package com.june.Lock8;

import java.util.concurrent.TimeUnit;
/**  多线程的8锁
 * 1、标准访问，请问先打印邮件还是短信？
 * 2、邮件方法暂停4秒钟，请问先打印邮件还是短信？
 * 3、新增一个普通方法hello()没有同步,请问先打印邮件还是hello？
 */
//1因为两个方法的调用者是同一个，所以 两个方法用的是同一个锁，先调用方法的先执行，
// 2第二个方法只有在第一个方法执行完释放锁之后才能执行。
//    3不是同步方法，不受锁的影响，所以不需要等待。其他线程共用了一把锁，所以还需要等待

public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        Thread.sleep(200);
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                phone.hello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}

class Phone {
    public void hello() {
        System.out.println(Thread.currentThread().getName()+"Hello");
    }

    public synchronized void sendEmail() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"sendEmail");
    }
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName()+"sendSMS");
    }
}

