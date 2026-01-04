package com.datastructure.linkedlist.doublylinkedlist.librarymanagementsystem;

public class LibraryApp {
    public static void main(String[] args) {

        LibraryDoublyLinkedList library = new LibraryDoublyLinkedList();

        library.addAtEnd(new Book(101, "Java", "James", "Programming", true));
        library.addAtEnd(new Book(102, "Python", "Guido", "Programming", true));
        library.addAtBeginning(new Book(103, "C++", "Bjarne", "Programming", false));
        library.addAtPosition(new Book(104, "DBMS", "Navathe", "Database", true), 2);

        System.out.println("Forward Display:");
        library.displayForward();

        System.out.println("\nReverse Display:");
        library.displayReverse();

        System.out.println("\nSearch by Author:");
        library.searchByAuthor("James");

        System.out.println("\nUpdate Availability:");
        library.updateAvailability(103, true);

        System.out.println("\nAfter Update:");
        library.displayForward();

        System.out.println("\nRemove Book 102:");
        library.removeById(102);

        library.countBooks();
        library.displayForward();
    }
}
