package com.example.algo;

import java.util.Scanner;

public class Polindrom {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter word : ");
        String word = scn.nextLine();
        System.out.println("Is polindrom : " + isPolindrom(word) + " this word " + word);

    }

    private static boolean isPolindrom(String word) {
        int left = 0 , right = word.length() - 1;
        while(left < right) {
            if(word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;

        }
        return true;
    }
}
