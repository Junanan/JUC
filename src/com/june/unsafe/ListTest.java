package com.june.unsafe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
//    1故障现象：ConcurrentModificationException
//    2导致原因：add 方法没有加锁
//    3解决方案：换一个集合类

//    1、List<String> list = new Vector<>();JDK1.0就存在了！
//    2、List<String> list = Collections.synchronizedList(new ArrayList<>());
//    3、List < String > list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
