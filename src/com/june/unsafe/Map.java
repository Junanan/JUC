package com.june.unsafe;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Map {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
        // 等价于
//        Map<String,String> map = new HashMap<>(16,0.75);
            // 工作中，常常会自己根据业务来写参数，提高效率
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
        String a = "sss";

    }
}
