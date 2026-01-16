package com.dayfour.examcell;
class Student {
    int rollNo;
    String name;
    int score;

    Student(int rollNo, String name, int score) {
        this.rollNo = rollNo;
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return rollNo + " | " + name + " | " + score;
    }
}
