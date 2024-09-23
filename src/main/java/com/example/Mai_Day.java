package com.example;

import com.example.algo.Search;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Mai_Day {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scn = new Scanner(System.in);
        Search search = new Search();
        System.out.print("Enter the number : ");
        int num = scn.nextInt();

        System.out.print("Enter size of array: ");
        int size = scn.nextInt();

        int[] arr = new int[size];

        System.out.println();

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(100);
        }

        Arrays.sort(arr);


        System.out.println("Array is  " + Arrays.toString(arr));

        int result = search.bynarySearch(arr, num);


        if(result == -1) {
            System.out.println("Not found");
        }else{
            System.out.println("Found at index " + result);
        }


    }
}
