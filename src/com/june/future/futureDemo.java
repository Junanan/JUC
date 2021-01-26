package com.june.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class futureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //没有返回值的 runAsync 异步调用
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "没有返回， update mysql ok");
        });
        System.out.println("111111"); // 先执行
        completableFuture.get();

        //有返回值的 供给型参数接口
         CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() +
                    "completableFuture2");
            int i = 10 / 0;
            return 1024;
        });
        System.out.println(completableFuture2.whenComplete((t, u) -> { //编译完成，正常结束输出
            System.out.println("===t:" + t); //正常结果
             System.out.println("===u:" + u);
//             报错的信息
        }).exceptionally(e -> { //结果异常，非正常结束
             System.out.println("=======exception:" + e.getMessage());
             return 555;
        }).get());
    }
}
