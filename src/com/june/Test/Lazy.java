package com.june.Test;

public class Lazy {
    public Lazy() {

    }
    private volatile static Lazy lazy;

    public static Lazy getInstance(){
        if (lazy == null){
            synchronized (Lazy.class){
                if (lazy ==null){
                    lazy = new Lazy();
                }
            }
        }
        return lazy;
    }
}
