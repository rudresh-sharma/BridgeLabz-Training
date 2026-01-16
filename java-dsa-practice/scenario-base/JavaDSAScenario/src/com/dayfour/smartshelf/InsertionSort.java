package com.dayfour.smartshelf;

public class InsertionSort {

    static void insertionSort(String[] books) {

        int n = books.length;

        for (int i = 1; i < n; i++) {

            String key = books[i];   // book to be inserted
            int j = i - 1;

            // compare full titles, not just first character
            while (j >= 0 && books[j].compareToIgnoreCase(key) > 0) {
                books[j + 1] = books[j];   // shift right
                j--;
            }

            books[j + 1] = key;   // insert book
        }
    }
}
