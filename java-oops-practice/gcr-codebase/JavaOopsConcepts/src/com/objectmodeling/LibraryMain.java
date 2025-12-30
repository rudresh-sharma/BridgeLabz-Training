package com.objectmodeling;

public class LibraryMain {
    public static void main(String[] args) {

        // Create independent Book objects
        Book book1 = new Book("Java Programming", "James Gosling");
        Book book2 = new Book("Python Basics", "Guido van Rossum");
        Book book3 = new Book("Data Structures", "Mark Allen Weiss");

        // Create two different libraries
        Library centralLibrary = new Library("Central Library");
        Library cityLibrary = new Library("City Library");

        // Add books to libraries
        centralLibrary.addBook(book1);
        centralLibrary.addBook(book2);

        cityLibrary.addBook(book2);   // Same book in another library
        cityLibrary.addBook(book3);

        // Display books
        centralLibrary.showBooks();
        cityLibrary.showBooks();

        System.out.println("\nDeleting Central Library...");

        // Remove central library
        centralLibrary = null;

        // Books still exist
        System.out.println("\nBooks still exist independently:");
        book1.displayBook();
        book2.displayBook();
        book3.displayBook();
    }
}

