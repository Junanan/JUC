package com.june.pool;

import java.util.concurrent.*;


public class demo1 {
    public static void main(String[] args) {
        //Executors工具类 , 3大方法
//        ExecutorService threadPool = Executors.newSingleThreadExecutor(); //单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5); //固定线程池
//        ExecutorService threadPool = Executors.newCachedThreadPool(); //可变化大小，伸缩
        //四个拒绝策略
//        new ThreadPoolExecutor.AbortPolicy()  队列满了丢任务，抛出异常
//        new ThreadPoolExecutor.DiscardPolicy() 队列满了丢任务，不抛出异常
//        new ThreadPoolExecutor.DiscardOldestPolicy() 将最早进入队列的任务 删，之后再尝试加入队列
//        new ThreadPoolExecutor.CallerRunsPolicy() 队列满了，交给主线程执行（main）
        //如何设置最大线程数
        //cpu密集型： max = CPU个数
        //io密集型: max = io任务数

        //ThreadPoolExecutor 有7个参数
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                    2,//核心窗口
                   3 ,//Runtime.getRuntime().availableProcessors(), //最大窗口  = 可用的CPU个数
                    3,//等待时间
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), //候客区3人
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()//拒绝策略 有四个  这个为默认，队列满了丢任务，抛出异常
        );
        try {
            //最大荷载 = 最大窗口+阻塞队列
            for (int i = 1; i <=12 ; i++) {
                //使用线程池创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"ok");
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //使用完后，必须关闭
            threadPool.shutdown();
        }
    }
}
