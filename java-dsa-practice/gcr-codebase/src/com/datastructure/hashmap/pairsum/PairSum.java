package com.datastructure.hashmap.pairsum;

import java.util.HashMap;

public class PairSum {

    /**
     * Method to find a pair of numbers in the array whose sum equals the target
     *
     * @param arr    input array of integers
     * @param target target sum to find
     */
    public static void findPair(int[] arr, int target) {
        // HashMap to store numbers we have seen so far
        HashMap<Integer, Boolean> map = new HashMap<>();

        // Iterate through each number in the array
        for (int num : arr) {
            // Calculate the complement needed to reach the target
            int needed = target - num;

            // Check if the complement exists in the map
            if (map.containsKey(needed)) {
                // If yes, print the pair and return immediately
                System.out.println("Pair found: " + num + " + " + needed + " = " + target);
                return;
            }

            // Otherwise, store the current number in the map for future lookup
            map.put(num, true);
        }

        // If no pair found after iterating through the array
        System.out.println("No pair found");
    }
}
