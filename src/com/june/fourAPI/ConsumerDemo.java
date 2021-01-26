package com.june.fourAPI;

import java.util.function.Consumer;
//消费型接口，有一个输入参数，没有返回值
public class ConsumerDemo {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
        Consumer<String> consumer = (s) -> {
            System.out.println(s);
        };
        consumer.accept("sssss");
    }
}
