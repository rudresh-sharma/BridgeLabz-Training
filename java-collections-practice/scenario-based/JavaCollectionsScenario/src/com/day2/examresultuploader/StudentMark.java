package com.day2.examresultuploader;

public class StudentMark {

    private String rollNo;
    private String name;
    private String subject;
    private int marks;

    public StudentMark(String rollNo, String name, String subject, int marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.subject = subject;
        this.marks = marks;
    }

    public String getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getSubject() { return subject; }
    public int getMarks() { return marks; }

    @Override
    public String toString() {
        return rollNo + " | " + name + " | " + subject + " | " + marks;
    }
}
