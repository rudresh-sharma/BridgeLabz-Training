package com.datastructure.linkedlist.doublylinkedlist.librarymanagementsystem;

public class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean available;

    public Book(int bookId, String title, String author, String genre, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
    }

    public void display() {
        System.out.println("ID: " + bookId + ", Title: " + title +
                ", Author: " + author + ", Genre: " + genre +
                ", Available: " + (available ? "Yes" : "No"));
    }
}
