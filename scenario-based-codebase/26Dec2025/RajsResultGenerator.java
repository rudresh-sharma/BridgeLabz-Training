/*

 Raj’s Result Generator ‍
Raj runs a coaching center. He needs to generate results.
● Input 5 subject marks.
● Calculate average.
● Switch to assign grades.
● for-loop to iterate over subjects.

*/

import java.util.Scanner;

public class RajsResultGenerator {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        double sum = 0;
        double[] marks = new double[5];

        // Input 5 subject marks using for loop
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter marks of subject " + (i + 1) + ": ");
            marks[i] = in.nextDouble();    
            sum += marks[i];
        }

        // Calculate average
        double average = sum / 5;
        System.out.println("\nAverage marks = " + average);

        // Switch to assign grade
        switch ((int) average / 10) {   
            case 10:
            case 9:
                System.out.println("Grade = A");
                break;
            case 8:
            case 7:
                System.out.println("Grade = B");
                break;
            case 6:
                System.out.println("Grade = C");
                break;
            case 5:
            case 4:
                System.out.println("Grade = D");
                break;
            default:
                System.out.println("Fail (-__-)");
        }

        in.close();
    }
}
