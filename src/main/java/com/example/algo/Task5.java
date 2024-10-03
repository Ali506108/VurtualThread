package com.example.algo;

import java.util.Arrays;
import java.util.Scanner;

public class Task5 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter word: ");
        String word = scn.nextLine();
        String lowerCase = word.toLowerCase();
        char[] arr = lowerCase.toCharArray();

        char[] arr_new = new char[arr.length];
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                arr_new[count] = arr[i]; // Сохраняем гласные
                count++;
            }
        }

        arr_new = Arrays.copyOf(arr_new, count);
        System.out.println("Number of vowels: " + count + " of arr " + Arrays.toString(arr_new));
    }
}
