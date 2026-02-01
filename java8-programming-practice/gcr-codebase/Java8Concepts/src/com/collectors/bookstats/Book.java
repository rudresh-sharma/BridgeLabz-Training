package com.collectors.bookstats;

public class Book {
    private String title;
    private String genre; // Fiction, Non-Fiction, Mystery, Sci-Fi, Biography
    private int pages;

    public Book(String title, String genre, int pages) {
	this.title = title;
	this.genre = genre;
	this.pages = pages;
    }

    public String getGenre() {
	return genre;
    }

    public int getPages() {
	return pages;
    }

    @Override
    public String toString() {
	return title + " (" + genre + ", " + pages + " pages)";
    }
}