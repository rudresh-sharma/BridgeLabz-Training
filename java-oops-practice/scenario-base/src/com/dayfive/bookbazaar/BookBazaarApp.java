package com.dayfive.bookbazaar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookBazaarApp {

    public static List<Book> inventory = new ArrayList<>();
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        // ===== Create EBooks =====
        Book e1 = new Ebook("Atomic Habits", "James Clear", 200, 4, "20MB", "PDF", "LIC1001");
        Book e2 = new Ebook("Think Like a Monk", "Jay Shetty", 250, 6, "18MB", "EPUB", "LIC1002");
        Book e3 = new Ebook("Rich Dad Poor Dad", "Robert Kiyosaki", 300, 5, "25MB", "PDF", "LIC1003");
        Book e4 = new Ebook("The Alchemist", "Paulo Coelho", 180, 8, "15MB", "MOBI", "LIC1004");
        Book e5 = new Ebook("Deep Work", "Cal Newport", 220, 7, "19MB", "EPUB", "LIC1005");

        // ===== Create PrintedBooks =====
        Book p1 = new PrintedBook("Clean Code", "Robert C. Martin", 550, 8, "464", 40.0);
        Book p2 = new PrintedBook("Head First Java", "Kathy Sierra", 650, 5, "720", 50.0);
        Book p3 = new PrintedBook("Design Patterns", "Erich Gamma", 800, 4, "395", 60.0);
        Book p4 = new PrintedBook("Effective Java", "Joshua Bloch", 750, 6, "416", 45.0);
        Book p5 = new PrintedBook("Java: The Complete Reference", "Herbert Schildt", 900, 3, "1248", 70.0);

        // ===== Add all books to inventory =====
        inventory.add(e1);
        inventory.add(e2);
        inventory.add(e3);
        inventory.add(e4);
        inventory.add(e5);
        inventory.add(p1);
        inventory.add(p2);
        inventory.add(p3);
        inventory.add(p4);
        inventory.add(p5);

        Order order = null;

        System.out.println("               Welcome to Book Bazaar");
        System.out.println("==================================================");

        System.out.println("\nHello user! Do you want to order a book? (1 for Yes / 0 for No)");
        int isOrder = in.nextInt();

        while (isOrder == 1) {

            int menuAgain;
            do {
                System.out.println("\nEnter your choice.");
                System.out.println("1. View a book collection");
                System.out.println("2. Order a book (using index)");
                System.out.println("3. View your ordered books");
                System.out.println("4. Print your bill");

                int userChoice = in.nextInt();
                in.nextLine();

                switch (userChoice) {
                    case 1: // View collection
                        System.out.println("Enter type of book you want to see:");
                        System.out.println("1. EBook");
                        System.out.println("2. PrintedBook");
                        int bookType = in.nextInt();
                        in.nextLine();

                        System.out.println("\nAvailable Books:");
                        for (int m = 0; m < inventory.size(); m++) {
                            Book b = inventory.get(m);

                            if (b.getStockCopies() > 0) {
                                if (bookType == 1 && b instanceof Ebook) {
                                    System.out.println("\nBook Index = " + m);
                                    b.printBookDetails(b);
                                } else if (bookType == 2 && b instanceof PrintedBook) {
                                    System.out.println("\nBook Index = " + m);
                                    b.printBookDetails(b);
                                }
                            }
                        }
                        break;

                    case 2: // Order books
                        System.out.print("Enter your name: ");
                        String name = in.nextLine();

                        System.out.println("Enter how many books you want to order:");
                        int noOfBooks = in.nextInt();
                        in.nextLine();

                        order = new Order(name);

                        for (int j = 1; j <= noOfBooks; j++) {
                            System.out.println("Enter book index:");
                            int index = in.nextInt();
                            in.nextLine();

                            if (index >= 0 && index < inventory.size()) {
                                Book selected = inventory.get(index);

                                if (selected.getStockCopies() > 0) {
                                    order.getBooks().add(selected);
                                    selected.decreaseStock();
                                } else {
                                    System.out.println("Sorry! This book is out of stock.");
                                }

                            } else {
                                System.out.println("Invalid index! Please try again.");
                                j--; // retry this order
                            }
                        }
                        break;

                    case 3: // View ordered books
                        if (order == null || order.getBooks().isEmpty()) {
                            System.out.println("No books ordered yet.");
                        } else {
                            System.out.println("\nBelow are your ordered books:");
                            for (int k = 0; k < order.getBooks().size(); k++) {
                                System.out.println("\nBook :" + (k + 1));
                                order.printBookDetails(order.getBooks().get(k));
                            }
                        }
                        break;

                    case 4: // Print bill
                        if (order == null || order.getBooks().isEmpty()) {
                            System.out.println("No books ordered yet.");
                        } else {
                            order.PrintBill();
                        }
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }

                System.out.println("\nDo you want to go back to menu? (1 for Yes / 0 for No)");
                menuAgain = in.nextInt();

            } while (menuAgain != 0);

            System.out.println("\nDo you want to order books again? (1 for Yes / 0 for No)");
            isOrder = in.nextInt();
        }

        System.out.println("\nThank you for visiting Book Bazaar!");
    }
}
