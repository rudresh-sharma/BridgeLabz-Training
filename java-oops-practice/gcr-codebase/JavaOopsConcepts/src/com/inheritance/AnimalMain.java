package com.inheritance;

public class AnimalMain {

    public static void main(String[] args) {

        // Polymorphism: Parent reference holding child objects
        Animal a1 = new Dog("Bruno", 3);
        Animal a2 = new Cat("Kitty", 2);
        Animal a3 = new Bird("Eagle", 4);

        // Runtime polymorphism (method overriding)
        a1.makeSound();   // Calls Dog's makeSound()
        a2.makeSound();   // Calls Cat's makeSound()
        a3.makeSound();   // Calls Bird's makeSound()
    }
}
