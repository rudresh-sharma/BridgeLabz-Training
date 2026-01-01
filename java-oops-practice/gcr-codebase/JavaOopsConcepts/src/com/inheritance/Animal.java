package com.inheritance;

// Superclass (Parent class)
public class Animal {

    // Common properties for all animals
    private String name;
    private int age;

    // Default constructor
    public Animal() {
        System.out.println("Animal object created");
    }

    // Parameterized constructor to initialize name and age
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter method to access animal name
    public String getName() {
        return name;
    }

    // Method to be overridden by subclasses
    public void makeSound() {
        System.out.println(name + " makes a sound");
    }
}
