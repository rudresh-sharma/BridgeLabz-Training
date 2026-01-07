/*
 * Linear Search Problem 1: Search for the First Negative Number
Problem:
You are given an integer array. Write a program that performs Linear Search to find the first negative number in the array. If a negative number is found, return its index. If no negative number is found, return -1.
Approach:
Iterate through the array from the start.
Check if the current element is negative.
If a negative number is found, return its index.
If the loop completes without finding a negative number, return -1.

 */
package com.algorithms.searchingalgorithms.linearsearch;

import java.util.Scanner;

public class FirstNegativeSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take array size input
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();

        int[] numbers = new int[n];

        // Take array elements input
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        // Find first negative number
        int index = findFirstNegative(numbers);

        if (index != -1) {
            System.out.println("First negative number found at index: " + index
                               + " (Value: " + numbers[index] + ")");
        } else {
            System.out.println("No negative number found in the array.");
        }

        sc.close();
    }

    // Linear search method
    public static int findFirstNegative(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                return i;
            }
        }
        return -1; // No negative number found
    }
}
