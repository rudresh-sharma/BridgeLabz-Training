package com.constructors.levelone;

public class Ebook extends LibraryBook {

    public Ebook(String ISBN, String title, String author) {
        super(ISBN, title, author);
    }

    // Access public and protected members
    public void displayEBookInfo() {
        System.out.println("EBook ISBN (public): " + ISBN);      // public
        System.out.println("EBook Title (protected): " + title); // protected
    }
}
