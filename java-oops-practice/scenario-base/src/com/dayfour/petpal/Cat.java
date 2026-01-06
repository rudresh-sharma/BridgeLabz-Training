package com.dayfour.petpal;

public class Cat extends PetBase implements IInteractable {

    public Cat(String name, String type, int age) {
        super(name, type, age);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " is meowing...");
    }

    @Override
    public void feed() {
        System.out.println(getName() + " is eating cat food");
        reduceHunger(15);
        changeEnergy(8);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is playing");
        increaseMood(15);
        changeEnergy(-10);
    }

    @Override
    public void sleep() {
        System.out.println(getName() + " is sleeping");
        changeEnergy(20);
    }
}
