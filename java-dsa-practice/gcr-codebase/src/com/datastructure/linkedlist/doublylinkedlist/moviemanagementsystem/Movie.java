package com.datastructure.linkedlist.doublylinkedlist.moviemanagementsystem;

public class Movie {
    String title;
    String director;
    int year;
    double rating;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    public void display() {
        System.out.println("Title: " + title + ", Director: " + director +
                ", Year: " + year + ", Rating: " + rating);
    }
}
