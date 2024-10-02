package com.example.algo;

public class Persan {

    public static void main(String[] args) {

        Thread thread_1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Start: " + i  + " " + Thread.currentThread().getName());
            }
        });


        Thread thread_2 = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                System.out.println("Start: " + i  + " " + Thread.currentThread().getName());
            }
        });

        thread_1.start();
        thread_2.start();

        try{
            thread_1.join();
            thread_2.join();
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("End");
    }
}
