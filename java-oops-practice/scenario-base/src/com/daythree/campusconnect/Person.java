package com.daythree.campusconnect;

public class Person {
    protected String name;
    protected String email;
    protected String id;

    public Person(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public void printDetails() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("ID: " + id);
    }
}
