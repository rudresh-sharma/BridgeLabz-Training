package com.inheritance;

// Bird is a subclass of Animal
public class Bird extends Animal {

    // Constructor calling parent constructor
    public Bird(String name, int age) {
        super(name, age);
    }

    // Overriding makeSound() method
    @Override
    public void makeSound() {
        System.out.println(getName() + " chirps: Chooooo...");
    }
}
