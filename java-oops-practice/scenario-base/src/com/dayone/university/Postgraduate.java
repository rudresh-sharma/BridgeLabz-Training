package com.dayone.university;

public class Postgraduate extends Student {
    public Postgraduate(String studentId, String name) {
        super(studentId, name);
    }

    @Override
    public void displayInfo() {
        System.out.println("Postgraduate Student: " + name + " (" + studentId + ")");
    }
}
