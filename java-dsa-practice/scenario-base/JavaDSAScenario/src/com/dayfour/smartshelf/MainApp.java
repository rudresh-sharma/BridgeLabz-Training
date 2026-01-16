/*
2. SmartShelf – Real-Time Book Arrangement (Insertion Sort)
Story: In a digital library kiosk, as users add books to their reading list, the system must keep
the list sorted alphabetically by title. Since books are added one at a time and the list is
mostly sorted, Insertion Sort fits perfectly.
Key Concepts:
● Online/real-time sorting
● Efficient for nearly sorted data
● Stable sorting by title
*/

package com.dayfour.smartshelf;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int n = in.nextInt();
        in.nextLine(); // clear buffer

        String[] books = new String[n];

        System.out.println("Enter book titles:");
        for (int i = 0; i < n; i++) {
            books[i] = in.nextLine();
        }

        // Sort using Insertion Sort
        InsertionSort.insertionSort(books);

        System.out.println("\nSorted SmartShelf:");
        for (String book : books) {
            System.out.println(book);
        }
    }
}
