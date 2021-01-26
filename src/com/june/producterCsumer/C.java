package com.june.producterCsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//精确通知顺序访问
public class C {

    public static void main(String[] args) {
        Data data  = new Data();
            new Thread(()->{
                for (int i = 0; i <9 ; i++) {
                    data.printA();
                }
            },"1").start();
            new Thread(()->{
                for (int i = 0; i <9 ; i++) {
                    data.printB();
                }
            },"2").start();
            new Thread(()->{
                for (int i = 0; i <9 ; i++) {
                    data.printC();
                }
            },"3").start();
    }

    static class Data { //资源类
        private Lock lock = new ReentrantLock();
        private Condition condition1 = lock.newCondition();
        private Condition condition2 = lock.newCondition();
        private Condition condition3 = lock.newCondition();
        private int number = 1;

        public void printA() {
            lock.lock();
            try {
                //业务, 判断，执行，通知
                while(number != 1){
                    condition1.await();
                }
                number = 2;
                System.out.println(Thread.currentThread().getName()+">AAAAA");
                //精确通知
                condition2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printB() {
            lock.lock();
            try {
                //业务, 判断，执行，通知
                while(number != 2){
                    condition2.await();
                }
                number = 3;
                System.out.println(Thread.currentThread().getName()+">BBBBB");
                condition3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printC() {
            lock.lock();
            try {
                //业务, 判断，执行，通知
                while(number != 3){
                    condition3.await();
                }
                number = 1;
                System.out.println(Thread.currentThread().getName()+">CCCCC");
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
