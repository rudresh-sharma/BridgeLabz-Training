package com.dayseven.skillforge;
public class Course {

    private String title;
    private String instructor;
    private double rating;     // Encapsulated
    private String[] modules;

    // Read-only internal reviews
    private int reviews;

    // Default constructor
    public Course(String title, String instructor) {
        this.title = title;
        this.instructor = instructor;
        this.modules = new String[]{"Intro", "Basics", "Advanced"};
        this.rating = 4.0;
        this.reviews = 10;
    }

    // Custom module constructor
    public Course(String title, String instructor, String[] modules) {
        this.title = title;
        this.instructor = instructor;
        this.modules = modules;
        this.rating = 4.5;
        this.reviews = 20;
    }

    // Encapsulation: protected rating logic
    protected void updateRating(double newRating) {
        this.rating = (this.rating + newRating) / 2;
    }

    public String getTitle() {
        return title;
    }

    // Read-only reviews
    public int getReviews() {
        return reviews;
    }

    public double getRating() {
        return rating;
    }
}
