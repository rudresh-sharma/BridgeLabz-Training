package com.datastructure.hashmap.longestconsecutivesequence;

public class MainApp {
    public static void main(String[] args) {

        // Input array of unsorted integers
        int[] nums = {100, 4, 200, 1, 3, 2};

        // Call the method to find the length of the longest consecutive sequence
        int length = LongestConsecutiveSequence.findLongestSequence(nums);

        // Print the result
        System.out.println("Length of Longest Consecutive Sequence: " + length);
        // Expected output: 4 (sequence is 1, 2, 3, 4)
    }
}
