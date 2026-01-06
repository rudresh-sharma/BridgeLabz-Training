package com.dayfour.petpal;

public class PetBase {

    // Common attributes
    private String name;
    private String type;
    private int age;

    // Encapsulated status fields
    private int hunger;
    private int mood;
    private int energy;

    // Constructor with random default values
    public PetBase(String name, String type, int age) {
        this.name = name;
        this.type = type;
        this.age = age;

        hunger = (int)(Math.random() * 50) + 30;   // 30–80
        mood   = (int)(Math.random() * 50) + 30;
        energy = (int)(Math.random() * 50) + 30;
    }

    public String getName() {
        return name;
    }

    // Encapsulated modifiers
    protected void reduceHunger(int value) {
        hunger = Math.max(0, hunger - value);
    }

    protected void increaseMood(int value) {
        mood = Math.min(100, mood + value);
    }

    protected void changeEnergy(int value) {
        energy = Math.min(100, Math.max(0, energy + value));
    }

    public void showStatus() {
        System.out.println("Hunger: " + hunger);
        System.out.println("Mood: " + mood);
        System.out.println("Energy: " + energy);
    }

    // Polymorphism
    public void makeSound() {
        System.out.println("Pet is making a sound...");
    }
}
