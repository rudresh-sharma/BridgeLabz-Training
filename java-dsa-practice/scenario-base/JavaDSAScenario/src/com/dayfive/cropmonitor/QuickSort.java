package com.dayfive.cropmonitor;

import java.util.ArrayList;

public class QuickSort {

    public static void quickSort(ArrayList<SensorData> data, int start, int end) {

        if (start >= end) return;

        int pivotIndex = partition(data, start, end);
        quickSort(data, start, pivotIndex - 1);
        quickSort(data, pivotIndex + 1, end);
    }

    public static int partition(ArrayList<SensorData> data, int start, int end) {

        SensorData pivot = data.get(end);
        int i = start - 1;

        for (int j = start; j < end; j++) {
            if (data.get(j).getTimestamps() < pivot.getTimestamps()) {
                i++;

                // ✅ correct swap
                SensorData temp = data.get(i);
                data.set(i, data.get(j));
                data.set(j, temp);
            }
        }

        // ✅ final pivot swap
        i++;
        SensorData temp = data.get(i);
        data.set(i, data.get(end));
        data.set(end, temp);

        return i;
    }
}
