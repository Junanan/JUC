package com.june.SaleTicker;

public class SaleTicker {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();
       new Thread(()->{
           for (int i = 1; i <40 ; i++) {
               ticket.sale();
           }
       },"A").start();
       new Thread(()->{
           for (int i = 1; i <40 ; i++) {
               ticket.sale();
           }
       },"B").start();
       new Thread(()->{
           for (int i = 1; i <40 ; i++) {
               ticket.sale();
           }
       },"B").start();
    }

    static class Ticket{
        private int number = 30;

        public synchronized void sale(){
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票，剩余："+number);
            }
        }
    }
}
