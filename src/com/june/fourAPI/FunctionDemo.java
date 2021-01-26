package com.june.fourAPI;


import java.util.function.Function;
//函数型接口，有一个输入，有一个输出
public class FunctionDemo {
    public static void main(String[] args) {
//        Function function = new Function<String, String>() {
//            //匿名内部类
//            @Override
//            public String apply(String s) {
//                return s;
//            }
//        };
        Function<String, String> function = (str) -> {return str;};
        System.out.println(function.apply("aa"));
    }

}
