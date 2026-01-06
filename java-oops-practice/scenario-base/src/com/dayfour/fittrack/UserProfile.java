package com.dayfour.fittrack;

public class UserProfile {
    private String name;
    private int age;
    private double weight;   // Encapsulated
    private int dailyTarget; // calorie goal

    // Default goal
    public UserProfile(String name, int age, double weight) {
        this(name, age, weight, 500);
    }

    // Custom goal
    public UserProfile(String name, int age, double weight, int dailyTarget) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.dailyTarget = dailyTarget;
    }

    public String getName() { return name; }
    public int getDailyTarget() { return dailyTarget; }

    public double getWeight() { return weight; } // read-only
}
