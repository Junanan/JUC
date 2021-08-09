package com.june.producterCsumer;

public class Test {
    public static void main(String[] args) {
        new Thread (() -> {
            for (int i = 0; i < 10; i++) {

            }
        },"a").start();
    }
    public static class data{
        private int num = 0;
        public synchronized void increment () throws InterruptedException {
            while (num != 0) {
                this.wait();
            }
            num --;
            this.notifyAll();
        }
    }
}
