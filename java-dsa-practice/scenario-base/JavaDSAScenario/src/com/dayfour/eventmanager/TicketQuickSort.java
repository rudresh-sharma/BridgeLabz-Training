package com.dayfour.eventmanager;
class TicketQuickSort {

    public static void quickSort(Ticket[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);

            quickSort(arr, low, p - 1);   // left side
            quickSort(arr, p + 1, high);  // right side
        }
    }

    // Partition based on ticket price
    private static int partition(Ticket[] arr, int low, int high) {

        double pivot = arr[high].price;   // last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].price <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                Ticket temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // place pivot at correct position
        Ticket temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
