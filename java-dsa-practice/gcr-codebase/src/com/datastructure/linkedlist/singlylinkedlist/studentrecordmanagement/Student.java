package com.datastructure.linkedlist.singlylinkedlist.studentrecordmanagement;

public class Student {
    int rollNo;
    String name;
    int age;
    String grade;

    // Constructor
    public Student(int rollNo, String name, int age, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    // Display student info
    public void display() {
        System.out.println("Roll: " + rollNo + ", Name: " + name +
                ", Age: " + age + ", Grade: " + grade);
    }
}
