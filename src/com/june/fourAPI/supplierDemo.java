package com.june.fourAPI;

import java.util.function.Supplier;

//供给型接口，没有输入参数，只有返回参数
public class supplierDemo {
    public static void main(String[] args) {
//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "1234";
//            }
//        };
        Supplier<String> supplier = ()->{return "1234";};
        System.out.println(supplier.get());
    }
}
