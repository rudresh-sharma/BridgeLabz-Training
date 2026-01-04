package com.datastructure.hashmap.zerosumsubarray;

import java.util.HashMap;
import java.util.ArrayList;

public class ZeroSumSubarray {

    /**
     * Method to find all subarrays whose sum is zero
     *
     * @param arr input array of integers (can contain positive and negative numbers)
     */
    public static void findSubarrays(int[] arr) {

        // HashMap to store cumulative sum and list of indices where this sum occurs
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int sum = 0; // variable to store cumulative sum

        // Initialize map with sum 0 at index -1 to handle subarrays starting from index 0
        map.put(0, new ArrayList<>());
        map.get(0).add(-1);

        // Traverse the array
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // add current element to cumulative sum

            // If cumulative sum has been seen before, there exists at least one subarray with sum 0
            if (map.containsKey(sum)) {
                ArrayList<Integer> list = map.get(sum);

                // For each previous index where the same sum occurred, print subarray
                for (int start : list) {
                    System.out.println("Subarray found from index "
                            + (start + 1) + " to " + i);
                }
            }

            // Add current index to the list of indices for this cumulative sum
            map.putIfAbsent(sum, new ArrayList<>());
            map.get(sum).add(i);
        }
    }
}
