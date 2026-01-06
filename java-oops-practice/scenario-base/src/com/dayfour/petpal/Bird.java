package com.dayfour.petpal;

public class Bird extends PetBase implements IInteractable {

    public Bird(String name, String type, int age) {
        super(name, type, age);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " is chirping...");
    }

    @Override
    public void feed() {
        System.out.println(getName() + " is eating bird food");
        reduceHunger(10);
        changeEnergy(5);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is flying and playing");
        increaseMood(20);
        changeEnergy(-10);
    }

    @Override
    public void sleep() {
        System.out.println(getName() + " is resting");
        changeEnergy(15);
    }
}
