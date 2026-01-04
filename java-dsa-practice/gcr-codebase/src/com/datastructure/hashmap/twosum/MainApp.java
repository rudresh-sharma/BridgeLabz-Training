package com.datastructure.hashmap.twosum;

public class MainApp {
    public static void main(String[] args) {

        // Input array of numbers
        int[] nums = {2, 7, 11, 15};

        // Target sum we want to find
        int target = 9;

        // Call TwoSum.findTwoSum() method to get indices of two numbers that sum up to target
        int[] indices = TwoSum.findTwoSum(nums, target);

        // Check if a valid pair was found
        if (indices[0] != -1)
            // Print the indices of the two numbers
            System.out.println("Two Sum indices: " + indices[0] + ", " + indices[1]);
        else
            // Print message if no pair exists
            System.out.println("No pair found");
    }
}
