package com.daythree.campusconnect;

public class Faculty extends Person {

    private String department;

    public Faculty(String name, String email, String id, String department) {
        super(name, email, id);
        this.department = department;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Department: " + department);
    }
}
