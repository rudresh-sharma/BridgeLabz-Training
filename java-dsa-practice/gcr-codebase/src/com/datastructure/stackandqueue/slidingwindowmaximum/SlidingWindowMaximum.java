package com.datastructure.stackandqueue.slidingwindowmaximum;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0)
            return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];

        Deque<Integer> dq = new LinkedList<>(); // stores indices

        for (int i = 0; i < n; i++) {

            // Remove indices outside the window
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Remove smaller elements from the back
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            dq.addLast(i);

            // Add maximum to result when first window is complete
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        return result;
    }
}
