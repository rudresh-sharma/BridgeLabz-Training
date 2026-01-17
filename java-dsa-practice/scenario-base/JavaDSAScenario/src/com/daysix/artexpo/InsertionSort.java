package com.daysix.artexpo;

import java.util.ArrayList;

public class InsertionSort {

    // Inserts element at index i into already sorted [0..i-1]
    public static void insertSorted(ArrayList<ArtistData> data, int i) {

        ArtistData key = data.get(i);
        int j = i - 1;

        // shift artists registered later than key
        while (j >= 0 && data.get(j)
                .getRegistrationTime()
                .isAfter(key.getRegistrationTime())) {

            data.set(j + 1, data.get(j));
            j--;
        }

        data.set(j + 1, key);
    }
}
