package com.june.producterCsumer;

import java.util.HashMap;

public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
    //判断等待，业务，通知
    static class Data {
        private int number = 0;

        //+1
        public synchronized void increment() throws InterruptedException {
            //防止虚假唤醒
            while (number != 0) {
                this.wait();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "当前线程" + number);
            //通知其他线程
            this.notifyAll();
        }

        //-1
        public synchronized void decrement() throws InterruptedException {
            while (number == 0) {
                this.wait();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "当前线程" + number);
            this.notifyAll();
        }
    }
}
