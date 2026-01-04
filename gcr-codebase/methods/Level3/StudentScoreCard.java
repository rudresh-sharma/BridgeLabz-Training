/*

Create a program to take input marks of students in 3 subjects: physics, chemistry, and maths. Compute the total, average, and the percentage score 

Hint => 
Take input for the number of students
Write a method to generate random 2-digit scores for Physics, Chemistry, and Math (PCM) for the students and return the scores. This method returns a 2D array with PCM scores for all students
Write a Method to calculate the total, average, and percentages for each student and return a 2D array with the corresponding values. Please ensure to round off the values to 2 Digits using the Math.round() method. 
Finally, write a Method to display the scorecard of all students with their scores, total, average, and percentage in a tabular format using "\t". 


*/

import java.util.Scanner;

public class StudentScoreCard {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = in.nextInt();

        int[][] pcmMarks = generatePCMMarks(n);
        double[][] results = calculateResults(pcmMarks);

        displayScoreCard(pcmMarks, results);

        in.close();
    }

    public static int[][] generatePCMMarks(int n) {
        int[][] marks = new int[n][3];

        for (int i = 0; i < n; i++) {
            marks[i][0] = (int)(Math.random() * 90) + 10; // Physics
            marks[i][1] = (int)(Math.random() * 90) + 10; // Chemistry
            marks[i][2] = (int)(Math.random() * 90) + 10; // Maths
        }
        return marks;
    }

    public static double[][] calculateResults(int[][] marks) {
        int n = marks.length;
        double[][] result = new double[n][3];

        for (int i = 0; i < n; i++) {
            double total = marks[i][0] + marks[i][1] + marks[i][2];
            double average = total / 3;
            double percentage = (total / 300) * 100;

            result[i][0] = Math.round(total * 100.0) / 100.0;
            result[i][1] = Math.round(average * 100.0) / 100.0;
            result[i][2] = Math.round(percentage * 100.0) / 100.0;
        }
        return result;
    }

    public static void displayScoreCard(int[][] marks, double[][] result) {

        System.out.println("\nID\tPhy\tChem\tMath\tTotal\tAvg\tPercent");

        for (int i = 0; i < marks.length; i++) {
            System.out.println(
                (i + 1) + "\t" +
                marks[i][0] + "\t" +
                marks[i][1] + "\t" +
                marks[i][2] + "\t" +
                result[i][0] + "\t" +
                result[i][1] + "\t" +
                result[i][2]
            );
        }
    }
}
