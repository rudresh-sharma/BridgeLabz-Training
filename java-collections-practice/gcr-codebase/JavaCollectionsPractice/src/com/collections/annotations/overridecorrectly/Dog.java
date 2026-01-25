package com.collections.annotations.overridecorrectly;

public class Dog extends Animal {

    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound();   // Calls overridden method
    }
}
