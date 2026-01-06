package com.algorithms.sortingalgorithms.selectionsort;

// Stores and manages exam scores
public class ExamScores {

    private int[] scores;

    // Constructor
    public ExamScores(int[] scores) {
        this.scores = scores;
    }

    // Getter
    public int[] getScores() {
        return scores;
    }

    // Display scores
    public void display() {
        for (int s : scores) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
