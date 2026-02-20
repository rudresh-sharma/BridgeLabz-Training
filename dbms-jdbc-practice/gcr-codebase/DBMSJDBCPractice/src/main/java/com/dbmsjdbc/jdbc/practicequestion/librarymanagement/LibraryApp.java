package com.dbmsjdbc.jdbc.practicequestion.librarymanagement;

import java.util.Scanner;

public class LibraryApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookService bookService = new BookService();
        StudentService studentService = new StudentService();
        BorrowService borrowService = new BorrowService();

        while (true) {

            System.out.println("\n===== LIBRARY SYSTEM =====");
            System.out.println("1. Add Book (Admin)");
            System.out.println("2. Add Student (Admin)");
            System.out.println("3. View Books");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Category: ");
                    String category = sc.nextLine();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    bookService.addBook(title, author, category, qty);
                    break;

                case 2:
                    System.out.print("Student Name: ");
                    String name = sc.nextLine();
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    studentService.addStudent(name, dept);
                    break;

                case 3:
                    bookService.viewBooks();
                    break;

                case 4:
                    System.out.print("Student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Book ID: ");
                    int bid = sc.nextInt();
                    borrowService.borrowBook(sid, bid);
                    break;

                case 5:
                    System.out.print("Record ID: ");
                    int rid = sc.nextInt();
                    borrowService.returnBook(rid);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }
}
