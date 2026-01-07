/*
 * Binary Search Problem 3: Search for a Target Value in a 2D Sorted Matrix
Problem:
You are given a 2D matrix where each row is sorted in ascending order, and the first element of each row is greater than the last element of the previous row. Write a program that performs Binary Search to find a target value in the matrix. If the value is found, return true. Otherwise, return false.
Approach:
Treat the matrix as a 1D array (flattened version).
Initialize left as 0 and right as rows * columns - 1.
Perform binary search:
Find the middle element index mid = (left + right) / 2.
Convert mid to row and column indices using row = mid / numColumns and col = mid % numColumns.
Compare the middle element with the target:
If it matches, return true.
If the target is smaller, search the left half by updating right = mid - 1.
If the target is larger, search the right half by updating left = mid + 1.
If the element is not found, return false.

 */

package com.algorithms.searchingalgorithms.binarysearch;

import java.util.Scanner;

public class SearchIn2DMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input matrix dimensions
        System.out.print("Enter the number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = sc.nextInt();

        int[][] matrix = new int[rows][cols];

        // Input matrix elements
        System.out.println("Enter the elements of the matrix row by row:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // Input target value
        System.out.print("Enter the target value to search: ");
        int target = sc.nextInt();

        boolean found = searchMatrix(matrix, target);

        if (found) {
            System.out.println("Target " + target + " found in the matrix.");
        } else {
            System.out.println("Target " + target + " not found in the matrix.");
        }

        sc.close();
    }

    // Binary search in 2D sorted matrix
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Convert mid to row and column indices
            int r = mid / cols;
            int c = mid % cols;

            if (matrix[r][c] == target) {
                return true; // Target found
            } else if (matrix[r][c] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        return false; // Target not found
    }
}
