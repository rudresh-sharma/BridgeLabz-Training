package com.algorithms.sortingalgorithms.insertionsort;
// Stores and manages employee IDs
public class EmployeeIDs {

    private int[] ids;

    // Constructor
    public EmployeeIDs(int[] ids) {
        this.ids = ids;
    }

    // Getter to access the array
    public int[] getIds() {
        return ids;
    }

    // Display employee IDs
    public void display() {
        for (int id : ids) {
            System.out.print(id + " ");
        }
        System.out.println();
    }
}