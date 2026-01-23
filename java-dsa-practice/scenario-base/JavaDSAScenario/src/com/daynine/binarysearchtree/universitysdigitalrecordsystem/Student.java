package com.daynine.binarysearchtree.universitysdigitalrecordsystem;

public class Student {

    private String name;
    private String rollNo;
    private Student left;
    private Student right;

    public Student(String name, String rollNo) {
        this.name = name;
        this.rollNo = rollNo;
        this.left = null;
        this.right = null;
    }

    // Copy constructor
    public Student(Student st) {
        this.name = st.name;
        this.rollNo = st.rollNo;
        this.left = null;
        this.right = null;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public Student getLeft() {
        return left;
    }

    public Student getRight() {
        return right;
    }

    public void setLeft(Student left) {
        this.left = left;
    }

    public void setRight(Student right) {
        this.right = right;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
}
