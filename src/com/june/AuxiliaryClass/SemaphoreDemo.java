package com.june.AuxiliaryClass;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//作用 ：并发限流
public class SemaphoreDemo {
    public static void main(String[] args) {
        //设置3个可用资源
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"--获取到资源");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName()+"--释放了资源");
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
