package com.daynine.birdsanctuary;
public class Duck extends Bird implements Flyable, Swimmable {
    public Duck(String id, String name) {
        super(id, name, "Duck");
    }

    public void fly() {
        System.out.println(getName() + " is flying.");
    }

    public void swim() {
        System.out.println(getName() + " is swimming.");
    }

    public String getAbilities() {
        return "Fly & Swim";
    }
}
