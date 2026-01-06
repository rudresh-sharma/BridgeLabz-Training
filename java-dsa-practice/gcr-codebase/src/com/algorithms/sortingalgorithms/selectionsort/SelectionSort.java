package com.algorithms.sortingalgorithms.selectionsort;

// Contains Selection Sort logic
public class SelectionSort {

    // Sort method
    public static void sort(int[] arr) {

        int n = arr.length;

        // Move boundary of unsorted part
        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;  // assume current is minimum

            // Find minimum in unsorted part
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap minimum with first unsorted element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}
