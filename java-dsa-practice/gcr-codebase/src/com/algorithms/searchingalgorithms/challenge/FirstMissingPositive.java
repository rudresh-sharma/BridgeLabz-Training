package com.algorithms.searchingalgorithms.binarysearch.challenge;

import java.util.Scanner;

public class FirstMissingPositive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input array size
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        // Input array elements
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int missing = findFirstMissingPositive(arr);

        System.out.println("The first missing positive integer is: " + missing);

        sc.close();
    }

    // Linear search approach to find first missing positive integer
    public static int findFirstMissingPositive(int[] arr) {
        int n = arr.length;

        // Mark numbers out of range as 0
        for (int i = 0; i < n; i++) {
            if (arr[i] <= 0 || arr[i] > n) {
                arr[i] = 0;
            }
        }

        // Use array index as a hash to mark presence
        for (int i = 0; i < n; i++) {
            int val = Math.abs(arr[i]);
            if (val >= 1 && val <= n) {
                if (arr[val - 1] > 0) {
                    arr[val - 1] = -arr[val - 1];
                } else if (arr[val - 1] == 0) {
                    arr[val - 1] = - (n + 1);
                }
            }
        }

        // Find first positive index
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0) {
                return i + 1;
            }
        }

        return n + 1;
    }
}
