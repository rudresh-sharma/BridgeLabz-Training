package com.dayfour.petpal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to PetPal");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Bird");

        int choice = in.nextInt();
        in.nextLine();

        System.out.print("Enter pet name: ");
        String name = in.nextLine();

        System.out.print("Enter pet age: ");
        int age = in.nextInt();

        PetBase pet = null;

        if(choice == 1)
            pet = new Dog(name, "Dog", age);
        else if(choice == 2)
            pet = new Cat(name, "Cat", age);
        else
            pet = new Bird(name, "Bird", age);

        pet.makeSound();  // Polymorphism

        IInteractable action = (IInteractable) pet;
        action.feed();
        action.play();
        action.sleep();

        pet.showStatus();
    }
}
