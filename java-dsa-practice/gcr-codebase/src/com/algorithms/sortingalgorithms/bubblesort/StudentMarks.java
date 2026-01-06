package com.algorithms.sortingalgorithms.bubblesort;
// Stores and manages student marks
public class StudentMarks {

    private int[] marks;

    // Constructor
    public StudentMarks(int[] marks) {
        this.marks = marks;
    }

    public int[] getMarks() {
        return marks;
    }

    // Display marks
    public void display() {
        for (int m : marks) {
            System.out.print(m + " ");
        }
        System.out.println();
    }
}
