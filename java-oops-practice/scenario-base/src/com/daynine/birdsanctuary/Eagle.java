package com.daynine.birdsanctuary;
public class Eagle extends Bird implements Flyable {
    public Eagle(String id, String name) {
        super(id, name, "Eagle");
    }

    public void fly() {
        System.out.println(getName() + " is soaring high.");
    }

    public String getAbilities() {
        return "Fly";
    }
}
