package com.example.algo;

public class Search {


    public static int bynarySearch(int[] arr , int target) {
        int left = 0, right = arr.length -1;


        while(left <= right) {
            int mid = (left + right) /2;

            if(arr[mid] == target) {
                return  mid;
            }else if(target < arr[mid]) {
                right = mid -1;
            }else{
                left = mid +1;
            }
        }

        return -1;
    }
}
