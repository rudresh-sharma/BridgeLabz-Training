package com.inheritance;

public class PersonRMS {

    protected String name;
    protected int id;

    public PersonRMS(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void displayInfo() {
        System.out.println("Name : " + name);
        System.out.println("ID : " + id);
    }
}
