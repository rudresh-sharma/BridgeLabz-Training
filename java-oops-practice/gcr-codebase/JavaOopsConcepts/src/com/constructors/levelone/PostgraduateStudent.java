package com.constructors.levelone;

public class PostgraduateStudent extends Student {

    public PostgraduateStudent(String rollNumber, String name, float cgpa) {
        super(rollNumber, name, cgpa);
    }

    // Demonstrating protected access
    public void displayPostgraduateInfo() {
        System.out.println("Postgraduate Student Name (protected): " + name);
    }

    // Modify protected variable
    public void updateName(String newName) {
        name = newName;   // Direct access to protected variable
    }
}
