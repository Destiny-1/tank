package com.zcyu.tank;

public enum Singleton {
    INSTANCE;

    private void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        Singleton.INSTANCE.m();
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                System.out.println(Singleton.INSTANCE.hashCode());
            }).start();
        }
    }
}
