package com.daynine.birdsanctuary;
public class Penguin extends Bird implements Swimmable {
    public Penguin(String id, String name) {
        super(id, name, "Penguin");
    }

    public void swim() {
        System.out.println(getName() + " is swimming fast.");
    }

    public String getAbilities() {
        return "Swim";
    }
}
