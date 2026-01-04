package com.dayone.university;

public class Undergraduate extends Student {
    public Undergraduate(String studentId, String name) {
        super(studentId, name);
    }

    @Override
    public void displayInfo() {
        System.out.println("Undergraduate Student: " + name + " (" + studentId + ")");
    }
}
