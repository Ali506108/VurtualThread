package com.example.algo;

import java.util.Scanner;

public class FirstTask {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter number : ");
        int number = scn.nextInt();

        System.out.println("Number is : " + number + " result " + sum(number));
    }

    private static int sum(int num) {
        int result = 0;
        if(num == 0 || num < -1){
            return 0;
        }else{
            while(num >0){
                result+=num%10;
                num/=10;
            }
        }
        return result;
    }
}
