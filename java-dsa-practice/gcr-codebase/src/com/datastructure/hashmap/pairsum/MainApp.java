package com.datastructure.hashmap.pairsum;

public class MainApp {
    public static void main(String[] args) {

        // Input array containing numbers
        int[] arr = {8, 7, 2, 5, 3, 1};

        // Target sum to find a pair for
        int target = 10;

        // Call the PairSum class method to find a pair with the given target
        // The method prints the pair if found or "No pair found" if not
        PairSum.findPair(arr, target);
    }
}
