package com.june.blockQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class blockQueue {

    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    // 抛出异常
    public static void test1() {
//        3 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
//        java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.add("A"));
        System.out.println(blockingQueue.add("B"));
        System.out.println(blockingQueue.add("C"));
//        System.out.println(blockingQueue.add("D"));

//        java.util.NoSuchElementException
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());

    }

    //无异常抛出
    public static void test2(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        //false
        System.out.println(blockingQueue.offer("A"));
        System.out.println(blockingQueue.offer("B"));
        System.out.println(blockingQueue.offer("C"));
//        System.out.println(blockingQueue.offer("D"));

//        null
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
    }
    //一直等待  阻塞
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("d"); // 一直等待
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
    }

    //超时退出
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);


        blockingQueue.offer("A");
        blockingQueue.offer("B");
        blockingQueue.offer("C");
        blockingQueue.offer("D",2, TimeUnit.SECONDS);  //等两秒 如果还是阻塞 就退出

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
    }
}
