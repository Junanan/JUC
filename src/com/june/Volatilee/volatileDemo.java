package com.june.Volatilee;


//代码验证可见性
public class volatileDemo {
    //不加volatile会死循环
    private  static volatile int num =0;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while(num == 0){

            }
        }).start();

        Thread.sleep(1000);
        num =1;
        System.out.println(num);
    }
}
