package com.example.algo;

import java.util.Scanner;

public class factorial {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("enter number: ");
        int number = scn.nextInt();
        System.out.println("Factorial of " + number + ": " + factorial(number));
    }


    public static int factorial(int number) {
        if (number == 0) {
            return 1;
        }
        return number * factorial(number - 1);
    }

}
