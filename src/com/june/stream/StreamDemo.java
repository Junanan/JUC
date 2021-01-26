package com.june.stream;

import com.sun.javaws.Main;

import java.util.Arrays;
import java.util.List;
//题目：请按照给出数据，找出同时满足以下条件的用户
//1、全部满足偶数ID
// 2、年龄大于24
//  3、用户名转为大写
//  4、用户名字母倒排序
//  5、只输出一个用户名字 limit
public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        list.stream().filter(user -> {return user.getId()%2 == 0;})
                .filter(user -> {return user.getAge()>24;})
                .map(user -> {return user.getUserName().toUpperCase();})
                .sorted((o1, o2) -> o2.compareTo(o1))
                .limit(1).forEach(System.out::println);

    }
}
