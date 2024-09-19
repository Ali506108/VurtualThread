package com.example.Aser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public static void main(String[] args) {

        try(ExecutorService service = Executors
                .newFixedThreadPool(1)){

            service.submit(() -> {
                threadLocal.set("Hello new Thread new");

                System.out.println("Thread 1 " + threadLocal.get());

            });

            service.submit(() -> {
                threadLocal.set("Hello new Thread 2");

                System.out.println("Thread 2 " + threadLocal.get());

            });

            service.submit(()-> {
                threadLocal.set("Ali thread");

                for (int i = 0; i < 10; i++) {
                    System.out.println(i + " Thread " + threadLocal.get());
                }
            });
        }
    }
}
