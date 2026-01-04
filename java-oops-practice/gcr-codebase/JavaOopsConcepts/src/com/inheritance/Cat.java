package com.inheritance;

// Cat is a subclass of Animal
public class Cat extends Animal {

    // Constructor calling parent constructor
    public Cat(String name, int age) {
        super(name, age);
    }

    // Overriding makeSound() method
    @Override
    public void makeSound() {
        System.out.println(getName() + " meows: Meowww...");
    }
}
