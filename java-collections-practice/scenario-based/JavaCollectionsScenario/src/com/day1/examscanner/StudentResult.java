package com.day1.examscanner;

public class StudentResult implements Comparable<StudentResult> {

    private String studentName;
    private int score;

    public StudentResult(String studentName, int score) {
        this.studentName = studentName;
        this.score = score;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(StudentResult other) {
        return Integer.compare(other.score, this.score); // Descending
    }

    @Override
    public String toString() {
        return studentName + " -> Score: " + score;
    }
}
