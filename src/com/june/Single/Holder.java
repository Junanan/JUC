package com.june.Single;

public class Holder {
    private Holder() {
    }

    public static Holder getInstance() {
        return InnerClass.holder;
    }

    private static class InnerClass {
        private static final Holder holder = new Holder();
    }
}
