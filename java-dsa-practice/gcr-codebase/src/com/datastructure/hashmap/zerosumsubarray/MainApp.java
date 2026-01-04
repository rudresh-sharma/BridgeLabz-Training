package com.datastructure.hashmap.zerosumsubarray;

public class MainApp {
    public static void main(String[] args) {

        // Input array containing positive and negative integers
        int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7};

        // Call the ZeroSumSubarray class method to find all subarrays with sum 0
        ZeroSumSubarray.findSubarrays(arr);

        // The method prints all subarrays found along with their start and end indices
    }
}
