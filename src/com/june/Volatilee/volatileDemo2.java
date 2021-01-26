package com.june.Volatilee;
//验证 volatile 不保证原子性
public class volatileDemo2 {
    //如何解决  -> 使用synchronize
    // 使用原子类  比synchronize更高效
    private volatile static int num = 0;
    public static void add() {
        num++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }, String.valueOf(i)).start();
        }
        // 需要等待上面20个线程都全部计算完毕，看最终结果
        while (Thread.activeCount() > 2) {
            // 默认一个 main线程 一个 gc 线程
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" "+num);
    }
}
