package com.datastructure.hashmap.twosum;

import java.util.HashMap;

public class TwoSum {

    /**
     * Method to find indices of two numbers in an array that sum up to the target
     *
     * @param nums   input array of integers
     * @param target target sum to find
     * @return array of size 2 with indices of numbers that sum to target, 
     *         or {-1, -1} if no such pair exists
     */
    public static int[] findTwoSum(int[] nums, int target) {
        // Create a HashMap to store numbers and their corresponding indices
        HashMap<Integer, Integer> map = new HashMap<>();

        // Iterate over the array
        for (int i = 0; i < nums.length; i++) {
            // Calculate the complement that would sum with nums[i] to make target
            int complement = target - nums[i];

            // If the complement exists in the map, we found the pair
            if (map.containsKey(complement)) {
                // Return indices: first index from map, second is current index
                return new int[]{map.get(complement), i};
            }

            // Otherwise, store the current number with its index in the map
            map.put(nums[i], i);
        }

        // If no pair found, return {-1, -1}
        return new int[]{-1, -1};
    }
}
