package com.designpattern.BookBuilderPattern;
public class BookDemo {
    public static void main(String[] args) {
        System.out.println("=== Book Builder Pattern Demo ===\n");
        
        // Book 1: Only mandatory field (title)
        Book book1 = new Book.Builder("The Minimalist Guide")
                .build();
        System.out.println("Book 1 (Only title):");
        System.out.println(book1);
        System.out.println();
        
        // Book 2: Title and author
        Book book2 = new Book.Builder("Clean Code")
                .setAuthor("Robert C. Martin")
                .build();
        System.out.println("Book 2 (Title + Author):");
        System.out.println(book2);
        System.out.println();
        
        // Book 3: Title, author, and genre
        Book book3 = new Book.Builder("1984")
                .setAuthor("George Orwell")
                .setGenre("Dystopian Fiction")
                .build();
        System.out.println("Book 3 (Title + Author + Genre):");
        System.out.println(book3);
        System.out.println();
        
        // Book 4: All fields
        Book book4 = new Book.Builder("Design Patterns")
                .setAuthor("Gang of Four")
                .setEdition(1)
                .setGenre("Software Engineering")
                .build();
        System.out.println("Book 4 (All fields):");
        System.out.println(book4);
        System.out.println();
        
        // Book 5: Title, edition, and genre (no author)
        Book book5 = new Book.Builder("Java Programming")
                .setEdition(11)
                .setGenre("Programming")
                .build();
        System.out.println("Book 5 (Title + Edition + Genre, no author):");
        System.out.println(book5);
        System.out.println();
        
        // Demonstrate error handling - trying to create book without title
        System.out.println("Attempting to create book with null title:");
        try {
            Book invalidBook = new Book.Builder(null).build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error caught: " + e.getMessage());
        }
        System.out.println();
        
        // Demonstrate error handling - trying to create book with empty title
        System.out.println("Attempting to create book with empty title:");
        try {
            Book invalidBook2 = new Book.Builder("").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error caught: " + e.getMessage());
        }
    }
}