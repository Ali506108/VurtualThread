package com.example.Aser;

import io.vertx.core.Vertx;

import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) {

        int num = 1;
        int limit = 100_000;
        int div = 1;
        int sum = 0;
        while( num <= limit ){

            if(num % div == 0 && div != num){
                sum+=div;
            }

            div++;

            if(div == num) {
                if(sum == num){
                    System.out.println(num + "great number ");
                }
                num++;
                div = 1;
                sum=0;
            }
          }

    }
}
