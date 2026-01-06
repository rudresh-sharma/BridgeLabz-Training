package com.dayfour.petpal;

public class Dog extends PetBase implements IInteractable {

    public Dog(String name, String type, int age) {
        super(name, type, age);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " is barking...");
    }

    @Override
    public void feed() {
        System.out.println(getName() + " is eating dog food");
        reduceHunger(20);
        changeEnergy(10);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is playing");
        increaseMood(20);
        changeEnergy(-15);
    }

    @Override
    public void sleep() {
        System.out.println(getName() + " is sleeping");
        changeEnergy(25);
    }
}
