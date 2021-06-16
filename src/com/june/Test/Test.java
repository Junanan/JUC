package com.june.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        Data data = new Data();

            new Thread(()->{
                for (int i = 0; i < 5; i++) {
                    try {
                        data.increament();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            },"A").start();
            new Thread(()->{
                for (int i = 0; i < 5; i++) {
                    try {
                        data.decreament();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            },"B").start();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                3,
                5,
                TimeUnit.SECONDS,
                new  LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }
    static class Data{
        private int num = 0;
        public synchronized void increament() throws InterruptedException {
            while(num != 0){
                this.wait();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+"当前线程"+num);
            this.notifyAll();
        }
        public synchronized void  decreament() throws InterruptedException {
            while(num == 0){
                this.wait();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"当前线程"+num);
            this.notifyAll();
        }
    }
}


