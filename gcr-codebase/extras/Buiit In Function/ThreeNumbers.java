/*



2. Maximum of Three Numbers:
○ Write a program that takes three integer inputs from the user and finds the
maximum of the three numbers.
○ Ensure your program follows best practices for organizing code into modular
functions, such as separate functions for taking input and calculating the
maximum value.

*/


import java.util.Scanner;

public class ThreeNumbers{
    Scanner in =  new Scanner(System.in);
    public static void main(String[] args) {

	// Taking inputs
        int[] numbers = takeInput();     
        int max = findMaximum(numbers); 

        System.out.println("\nThe maximum number is: " + max);
 
    }

    // Method to take three integers from user
    public static int[] takeInput() {
        Scanner in = new Scanner(System.in);
        int[] arr = new int[3];

        System.out.print("Enter first number: ");
        arr[0] = in.nextInt();

        System.out.print("Enter second number: ");
        arr[1] = in.nextInt();

        System.out.print("Enter third number: ");
        arr[2] = in.nextInt();

        return arr;
    }

    // Method to find maximum of three numbers
    public static int findMaximum(int[] arr) {
        int max = arr[0];

        if (arr[1] > max) {
            max = arr[1];
        }
        if (arr[2] > max) {
            max = arr[2];
        }

        return max;
    }



}
