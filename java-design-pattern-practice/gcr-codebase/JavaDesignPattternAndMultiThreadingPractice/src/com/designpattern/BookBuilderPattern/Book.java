package com.designpattern.BookBuilderPattern;
public class Book {
    // Final fields - immutable once created
    private final String title;      // Mandatory
    private final String author;     // Optional
    private final Integer edition;   // Optional
    private final String genre;      // Optional
    
    // Private constructor - can only be called by Builder
    private Book(Builder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.edition = builder.edition;
        this.genre = builder.genre;
    }
    
    // Getters
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public Integer getEdition() {
        return edition;
    }
    
    public String getGenre() {
        return genre;
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", edition=" + edition +
                ", genre='" + genre + '\'' +
                '}';
    }
    
    // Static nested Builder class
    public static class Builder {
        // Mandatory field
        private final String title;
        
        // Optional fields - initialized to default values
        private String author;
        private Integer edition;
        private String genre;
        
        // Constructor with mandatory field
        public Builder(String title) {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title is mandatory and cannot be null or empty");
            }
            this.title = title;
        }
        
        // Methods for optional fields - return Builder for method chaining
        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }
        
        public Builder setEdition(Integer edition) {
            this.edition = edition;
            return this;
        }
        
        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }
        
        // Build method to create the Book instance
        public Book build() {
            return new Book(this);
        }
    }
}