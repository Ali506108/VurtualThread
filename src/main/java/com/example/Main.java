package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {


        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("this is task 1");
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Error");
            }
        };


        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Run");
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        Thread t5 = new Thread(() -> {
            for(int i = 0 ; i<10; i++) {
                System.out.println(i + " " + Thread.currentThread().getName());
            }
        });

        t5.start();
        t1.start();
        t2.start();
        t3.start();


        try{
            t1.join();
            t2.join();
            t3.join();
            t5.join();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println("finally");
        }

        try(ExecutorService vte = Executors.newFixedThreadPool(3)){
            vte.submit(t1);
            vte.submit(t3);
            vte.submit(t2);
        }

    }
}