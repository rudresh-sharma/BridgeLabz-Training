package com.dayfive.robowarehouse;

public class InsertionSort {

    // inserts element at index i into already sorted [0..i-1]
    public static void insertSorted(int[] weights, int i) {

        int key = weights[i];
        int j = i - 1;

        while (j >= 0 && weights[j] > key) {
            weights[j + 1] = weights[j];
            j--;
        }

        weights[j + 1] = key;
    }
}
