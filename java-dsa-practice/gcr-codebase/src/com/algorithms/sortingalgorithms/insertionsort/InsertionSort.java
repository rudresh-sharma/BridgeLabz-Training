package com.algorithms.sortingalgorithms.insertionsort;

// Contains insertion sort logic
public class InsertionSort {

    // Sort method
    public static void sort(int[] arr) {

        // Loop from second element (first element is already sorted)
        for (int i = 1; i < arr.length; i++) {

            int key = arr[i];   // Element to be inserted
            int j = i - 1;

            // Shift elements of sorted part that are greater than key
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Place key in correct position
            arr[j + 1] = key;
        }
    }
}
