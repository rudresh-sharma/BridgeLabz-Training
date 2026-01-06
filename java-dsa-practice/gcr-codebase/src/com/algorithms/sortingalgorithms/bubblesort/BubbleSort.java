package com.algorithms.sortingalgorithms.bubblesort;


// Contains bubble sort logic
public class BubbleSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        // Traverse multiple times
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Compare adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no swapping happened → already sorted
            if (!swapped)
                break;
        }
    }
}