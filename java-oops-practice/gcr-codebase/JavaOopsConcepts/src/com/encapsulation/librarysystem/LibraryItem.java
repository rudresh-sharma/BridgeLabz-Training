package com.encapsulation.librarysystem;

public abstract class LibraryItem {

    private String itemId;
    private String title;
    private String author;

    protected boolean available = true;
    private String borrowerName;   // Encapsulated (secured)

    public LibraryItem(String id, String title, String author) {
        this.itemId = id;
        this.title = title;
        this.author = author;
    }

    // Encapsulation (Getters)
    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    protected void setBorrower(String name) {
        this.borrowerName = name;
    }

    protected String getBorrower() {
        return borrowerName;
    }

    // Concrete method
    public void getItemDetails() {
        System.out.println("ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Available: " + (available ? "Yes" : "No"));
    }

    // Polymorphism
    public abstract int getLoanDuration();
}
