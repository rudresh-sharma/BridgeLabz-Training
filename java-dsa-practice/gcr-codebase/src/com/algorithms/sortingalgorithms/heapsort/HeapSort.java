package com.algorithms.sortingalgorithms.heapsort;
public class HeapSort {

    public static void heapSort(Applicant[] arr) {
        int n = arr.length;

        // Build Max Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements
        for (int i = n - 1; i > 0; i--) {
            Applicant temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(Applicant[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left].getSalary() > arr[largest].getSalary())
            largest = left;

        if (right < n && arr[right].getSalary() > arr[largest].getSalary())
            largest = right;

        if (largest != i) {
            Applicant swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
}
