package com.inheritance;

public class LibraryMain {

    public static void main(String[] args) {

        // Creating Author object (which is also a Book)
        Book book = new Author(
                "Java Programming",
                2024,
                "James Gosling",
                "Father of the Java Programming Language"
        );

        // Polymorphism: Book reference calling Author method
        book.displayInfo();
    }
}
