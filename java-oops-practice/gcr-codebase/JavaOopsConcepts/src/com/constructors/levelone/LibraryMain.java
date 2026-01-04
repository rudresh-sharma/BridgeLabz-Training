package com.constructors.levelone;

public class LibraryMain {

    public static void main(String[] args) {

        LibraryBook b1 = new LibraryBook("ISBN101", "Java Programming", "James Gosling");

        // Accessing public
        System.out.println("ISBN from Book: " + b1.ISBN);
        // Accessing private using getter
        System.out.println("Author from Book: " + b1.getAuthor());

        // Modifying private using setter
        b1.setAuthor("Herbert Schildt");
        System.out.println("Updated Author: " + b1.getAuthor());

        System.out.println("-------------------------");

        // Subclass object
        Ebook eb = new Ebook("ISBN202", "Advanced Java", "Oracle");

        // Demonstrating protected and public
        eb.displayEBookInfo();
    }
}
