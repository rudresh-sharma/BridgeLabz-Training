/*

Create a program to find the maximum number of handshakes among N number of students.
Hint => 
Get integer input for numberOfStudents variable.
Use the combination = (n * (n - 1)) / 2 formula to calculate the maximum number of possible handshakes.
Display the number of possible handshakes.

*/

import java.util.Scanner;

// Program to calculate the maximum number of handshakes among students
public class MaximumHandshake {
    public static void main(String[] args) {

        // ----- input -----
        // create Scanner object to read input from user
        Scanner input = new Scanner(System.in);

        // ask user to enter the number of students
        System.out.print("Enter the number of students: ");
        int n = input.nextInt();

        // ----- calculation -----
        // maximum handshakes formula: n * (n-1) / 2
        int maximumHandshakes = (n * (n - 1)) / 2;

        // ----- output -----
        // print total number of handshakes
        System.out.println(
            "Total number of handshakes among " + n + " is " + maximumHandshakes
        );
    }
}
