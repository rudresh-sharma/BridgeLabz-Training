package com.designpattern.smartuniversitylibrarymanagementsystem;
public class Student implements User {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void showRole() {
        System.out.println(name + " is a Student.");
    }

    public void update(String msg) {
        System.out.println(name + " notified: " + msg);
    }
}