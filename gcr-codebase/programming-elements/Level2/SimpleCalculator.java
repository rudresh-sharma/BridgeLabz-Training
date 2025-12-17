/*

Write a program to create a basic calculator that can perform addition, subtraction, multiplication, and division. The program should ask for two numbers (floating point) and perform all the operations
Hint => 
Create a variable number1 and number 2 and take user inputs.
Perform Arithmetic Operations of addition, subtraction, multiplication and division and assign the result to a variable and finally print the result
I/P => number1, number2
O/P => The addition, subtraction, multiplication and division value of 2 numbers ___ and ___ is ___, ____, ____, and ___


*/

import java.util.Scanner;

// Program to perform basic arithmetic operations on two numbers
public class SimpleCalculator {
    public static void main(String[] args) {

        // ----- input -----
        // create Scanner object to read input from user
        Scanner input = new Scanner(System.in);

        // ask user to enter two numbers
        System.out.print("Enter the two Numbers: ");
        float number1 = input.nextFloat();
        float number2 = input.nextFloat();

        // ----- calculation -----
        // perform addition, subtraction, multiplication, and division
        float addition = number1 + number2;
        float subtraction = number1 - number2;
        float multiplication = number1 * number2;
        float division = number1 / number2;

        // ----- output -----
        // display the results
        System.out.println(
            "The addition, subtraction, multiplication and division value of 2 numbers " +
            number1 + " and " + number2 + " is " +
            addition + " , " + subtraction + " , and " + multiplication + " , " + division
        );
    }
}
