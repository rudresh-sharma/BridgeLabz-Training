package com.dayfour.zipzipmart;
class SalesMergeSort {

    public static void mergeSort(Sale[] arr, int left, int right) {
        if (left < right) {

            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);       // divide left
            mergeSort(arr, mid + 1, right);  // divide right

            merge(arr, left, mid, right);    // conquer
        }
    }

    // Sorting by date first, then amount (stable)
    private static void merge(Sale[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        Sale[] L = new Sale[n1];
        Sale[] R = new Sale[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {

            // Compare by date first
            if (L[i].date.compareTo(R[j].date) < 0) {
                arr[k++] = L[i++];
            }
            else if (L[i].date.compareTo(R[j].date) > 0) {
                arr[k++] = R[j++];
            }
            else {
                // Same date → compare amount (stable)
                if (L[i].amount <= R[j].amount) {
                    arr[k++] = L[i++];
                } else {
                    arr[k++] = R[j++];
                }
            }
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }
}
