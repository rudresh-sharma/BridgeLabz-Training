package com.datastructure.hashmap.longestconsecutivesequence;

import java.util.HashSet;

public class LongestConsecutiveSequence {

    /**
     * Method to find the length of the longest consecutive elements sequence
     *
     * @param nums input array of integers (unsorted)
     * @return length of the longest consecutive sequence
     */
    public static int findLongestSequence(int[] nums) {

        // Use a HashSet to store all elements for O(1) lookup
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);

        int longest = 0; // variable to store length of the longest sequence

        // Iterate through each number in the set
        for (int num : set) {

            // Only start counting if this is the start of a sequence
            // i.e., previous number (num - 1) does not exist in the set
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int length = 1; // current consecutive sequence length

                // Count consecutive numbers
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    length++;
                }

                // Update longest sequence if current is longer
                longest = Math.max(longest, length);
            }
        }

        // Return the length of the longest consecutive sequence
        return longest;
    }
}
