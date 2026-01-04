package com.inheritance;

// Author inherits Book
public class Author extends Book {

    private String name;
    private String bio;

    // Constructor
    public Author(String title, int publicationYear, String name, String bio) {
        super(title, publicationYear);   // call Book constructor
        this.name = name;
        this.bio = bio;
    }

    // Overriding displayInfo()
    @Override
    public void displayInfo() {
        super.displayInfo();   // print book details
        System.out.println("Author Name : " + name);
        System.out.println("Author Bio : " + bio);
    }
}
