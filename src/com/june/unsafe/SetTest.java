package com.june.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

// 1  出现异常：ConcurrentModificationException
//    2导致原因：add 方法没有加锁
//    3解决方案：换一个集合类
public class SetTest {
    public static void main(String[] args) {
//        HashSet<String> set = new HashSet<>();
//        Set<Object> set = Collections.synchronizedSet(new HashSet<>());
        CopyOnWriteArraySet<Object> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30 ; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }

    }
}
