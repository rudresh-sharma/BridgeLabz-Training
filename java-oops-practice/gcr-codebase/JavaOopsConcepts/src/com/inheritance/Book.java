package com.inheritance;

public class Book {

    // Common book attributes
    private String title;
    private int publicationYear;

    // Constructor
    public Book(String title, int publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    // Method to display book details
    public void displayInfo() {
        System.out.println("Book Title : " + title);
        System.out.println("Publication Year : " + publicationYear);
    }
}
