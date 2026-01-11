package com.dayone.bookshelf;

import java.util.Scanner;

public class LibrarySystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookShelf myShelf = new BookShelf();

        // --- Hardcode 2 books per genre ---
        BookNode b1 = new BookNode("The Alchemist", "Paulo Coelho", "Fiction");
        BookNode b2 = new BookNode("To Kill a Mockingbird", "Harper Lee", "Fiction");

        BookNode b3 = new BookNode("A Brief History of Time", "Stephen Hawking", "Science");
        BookNode b4 = new BookNode("The Selfish Gene", "Richard Dawkins", "Science");

        BookNode b5 = new BookNode("Sapiens", "Yuval Noah Harari", "History");
        BookNode b6 = new BookNode("Guns, Germs, and Steel", "Jared Diamond", "History");

        // Add books to the shelf
        myShelf.addBook(b1);
        myShelf.addBook(b2);
        myShelf.addBook(b3);
        myShelf.addBook(b4);
        myShelf.addBook(b5);
        myShelf.addBook(b6);

        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Display Books by Genre");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Display books
                    System.out.print("Enter genre to display: ");
                    String genre = sc.nextLine();
                    myShelf.displayBooksByGenre(genre);
                    break;
                    
                    
                case 2:
                	 // Add book
                    System.out.print("Enter title of book : ");
                    String addTitle = sc.nextLine();
                    System.out.print("Enter author of book : ");
                    String addAuthor = sc.nextLine();
                    System.out.print("Enter genre of the book: ");
                    String addGenre = sc.nextLine();
                    
                    BookNode b = new BookNode(addTitle, addAuthor, addGenre);
                    myShelf.addBook(b);
                    
                    break;

                case 4:
                    // Borrow book
                    System.out.print("Enter title of book to borrow: ");
                    String borrowTitle = sc.nextLine();
                    System.out.print("Enter genre of the book: ");
                    String borrowGenre = sc.nextLine();
                    myShelf.borrowBook(borrowTitle, borrowGenre);
                    break;

                case 5:
                    // Return book
                    System.out.print("Enter title of book to return: ");
                    String returnTitle = sc.nextLine();
                    System.out.print("Enter genre of the book: ");
                    String returnGenre = sc.nextLine();
                    BookNode returnedBook = new BookNode(returnTitle, "Unknown", returnGenre);
                    myShelf.returnBook(returnedBook);
                    System.out.println(returnTitle + " returned successfully!");
                    break;

                case 6:
                    // Exit
                    exit = true;
                    System.out.println("Exiting Library System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }

        sc.close();
    }
}
