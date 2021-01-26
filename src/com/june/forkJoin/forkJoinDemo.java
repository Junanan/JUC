package com.june.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class forkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();time: 17072
//        test2();time: 1333
//        test3();time: 118
    }

    public static void test1() {
        //普通线程实现
        Long x = 0L;
        Long y = 2000000000L;
        long l = System.currentTimeMillis();
        for (Long i = 0L; i <= y; i++) {
            x += i;
        }

        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + x + " time: " + (l1 - l));
    }
    // forkjoin这个框架针对的是大任务执行，效率才会明显的看出来有提升，于是我把总数调大到 20亿。
    public static void test2() throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        //ForkJoin实现
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long>  forkJoinTest = new forkJoinTest(0L, 200000000L);
        ForkJoinTask<Long> task = forkJoinPool.submit(forkJoinTest);
        Long result = task.get();
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + result +" time: " + (l1-l));
    }
    public static void test3(){
        //Java 8 并行流的实现
        long l = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0L, 200000000L).parallel().reduce(0, Long::sum);
        // reduce(0, Long::sum) == reduce(0, (a, b) -> a + b);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + reduce+" time: " + (l1-l));

    }
}
