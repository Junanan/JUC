package com.june.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable callable = new MyCallable();
        //适配器
        FutureTask futureTask = new FutureTask(callable);
        new Thread(futureTask,"A").start();
        //第二次执行会有缓存
        new Thread(futureTask,"B").start();

        Object o = futureTask.get();
        System.out.println(o);

    }

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println(1111);
            return "xixi";
        }
    }
}
