/*
 * Binary Search Problem 1: Find the Rotation Point in a Rotated Sorted Array
Problem:
You are given a rotated sorted array. Write a program that performs Binary Search to find the index of the smallest element in the array (the rotation point).
Approach:
Initialize left as 0 and right as n - 1.
Perform a binary search:
Find the middle element mid = (left + right) / 2.
If arr[mid] > arr[right], then the smallest element is in the right half, so update left = mid + 1.
If arr[mid] < arr[right], the smallest element is in the left half, so update right = mid.
Continue until left equals right, and then return arr[left] (the rotation point).

 */

package com.algorithms.searchingalgorithms.binarysearch;

import java.util.Scanner;

public class RotationPointFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input array size
        System.out.print("Enter the number of elements in the rotated sorted array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        // Input array elements
        System.out.println("Enter the elements of the array (space-separated, in rotated sorted order):");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int rotationIndex = findRotationPoint(arr);

        System.out.println("The rotation point (index of smallest element) is at index: " + rotationIndex
                           + " (Value: " + arr[rotationIndex] + ")");

        sc.close();
    }

    // Binary search to find rotation point
    public static int findRotationPoint(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid element is greater than rightmost, rotation point is in right half
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                // Else, rotation point is in left half (including mid)
                right = mid;
            }
        }

        // left == right, rotation point found
        return left;
    }
}
