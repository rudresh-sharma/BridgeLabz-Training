package com.daynine.birdsanctuary;

import java.io.*;
import java.util.ArrayList;

public class Sanctuary {

    private ArrayList<Bird> birds;

    // Correct project-based file path
    private final String FILE =
    	    System.getProperty("user.dir") +
    	    "/scenario-base/src/com/daynine/birdsanctuary/birds.dat";


    public Sanctuary() {
        load();
    }

    // ------------------ ADD ------------------
    public void addBird(Bird b) {
        birds.add(b);
        System.out.println("Bird added successfully.");
    }

    // ------------------ DISPLAY ALL ------------------
    public void displayAll() {
        if (birds.isEmpty()) {
            System.out.println("No birds in sanctuary.");
            return;
        }

        for (Bird b : birds) {
            System.out.println(
                    b.getId() + " | " +
                    b.getName() + " | " +
                    b.getSpecies() + " | " +
                    b.getAbilities()
            );
        }
    }

    // ------------------ FLYING ------------------
    public void displayFlyers() {
        for (Bird b : birds)
            if (b instanceof Flyable)
                System.out.println(b.getName());
    }

    // ------------------ SWIMMING ------------------
    public void displaySwimmers() {
        for (Bird b : birds)
            if (b instanceof Swimmable)
                System.out.println(b.getName());
    }

    // ------------------ BOTH ------------------
    public void displayBoth() {
        for (Bird b : birds)
            if (b instanceof Flyable && b instanceof Swimmable)
                System.out.println(b.getName());
    }

    // ------------------ DELETE ------------------
    public void deleteById(String id) {
        birds.removeIf(b -> b.getId().equals(id));
        System.out.println("Bird deleted if it existed.");
    }

    // ------------------ REPORT ------------------
    public void report() {
        int fly=0, swim=0, both=0, none=0;

        for (Bird b : birds) {
            boolean f = b instanceof Flyable;
            boolean s = b instanceof Swimmable;

            if (f && s) both++;
            else if (f) fly++;
            else if (s) swim++;
            else none++;
        }

        System.out.println("Flyable: " + fly);
        System.out.println("Swimmable: " + swim);
        System.out.println("Both: " + both);
        System.out.println("Neither: " + none);
    }

    // ------------------ SAVE ------------------
    public void save() {
        try {
            File file = new File(FILE);
            file.getParentFile().mkdirs();   // create folders if missing

            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
            o.writeObject(birds);
            o.close();

            System.out.println("Data saved to:");
            System.out.println(file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ------------------ LOAD ------------------
    public void load() {
        try {
            ObjectInputStream i = new ObjectInputStream(new FileInputStream(FILE));
            birds = (ArrayList<Bird>) i.readObject();
            i.close();
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            birds = new ArrayList<>();
            System.out.println("No previous data found.");
        }
    }
}
