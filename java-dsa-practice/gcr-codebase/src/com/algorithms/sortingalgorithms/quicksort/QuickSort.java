package com.algorithms.sortingalgorithms.quicksort;

// Contains Quick Sort logic
public class QuickSort {

    // Main quick sort method
    public static void sort(double[] arr, int low, int high) {
        if (low < high) {

            // Partition the array
            int p = partition(arr, low, high);

            // Recursively sort left and right parts
            sort(arr, low, p - 1);
            sort(arr, p + 1, high);
        }
    }

    // Partition using last element as pivot
    private static int partition(double[] arr, int low, int high) {

        double pivot = arr[high];   // pivot element
        int i = low - 1;

        // Rearrange elements based on pivot
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;

                // Swap arr[i] and arr[j]
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Place pivot at correct position
        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
