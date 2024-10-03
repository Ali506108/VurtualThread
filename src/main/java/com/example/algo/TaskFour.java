package com.example.algo;

import java.util.Arrays;
import java.util.Scanner;

public class TaskFour {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter length of array : ");
        int length = scn.nextInt();
        int[] arr = new int[length];

        for (int i = 0; i < length; i++) {
            System.out.print("Enter number : ");
            arr[i] = scn.nextInt();
        }

        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }

        System.out.println("Max number : " + max + " of arr " + Arrays.toString(arr));
    }
}
