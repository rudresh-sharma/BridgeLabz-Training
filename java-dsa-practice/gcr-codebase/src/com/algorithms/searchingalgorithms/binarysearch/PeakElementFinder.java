/*
 * Binary Search Problem 2: Find the Peak Element in an Array
Problem:
A peak element is an element that is greater than its neighbors. Write a program that performs Binary Search to find a peak element in an array. If there are multiple peak elements, return any one of them.
Approach:
Initialize left as 0 and right as n - 1.
Perform a binary search:
Find the middle element mid = (left + right) / 2.
If arr[mid] > arr[mid - 1] and arr[mid] > arr[mid + 1], arr[mid] is a peak element.
If arr[mid] < arr[mid - 1], then search the left half, updating right = mid - 1.
If arr[mid] < arr[mid + 1], then search the right half, updating left = mid + 1.
Continue until a peak element is found.

 */

package com.algorithms.searchingalgorithms.binarysearch;

import java.util.Scanner;

public class PeakElementFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input array size
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        // Input array elements
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int peakIndex = findPeakElement(arr);

        System.out.println("A peak element is at index: " + peakIndex + " (Value: " + arr[peakIndex] + ")");

        sc.close();
    }

    // Binary search to find a peak element
    public static int findPeakElement(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Compare mid with next element
            if (arr[mid] < arr[mid + 1]) {
                // Peak must be in the right half
                left = mid + 1;
            } else {
                // Peak is in the left half or at mid
                right = mid;
            }
        }

        // left == right, peak element found
        return left;
    }
}
