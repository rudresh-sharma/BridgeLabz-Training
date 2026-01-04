/*

Write a program to check if the first is the smallest of the 3 numbers.
I/P => number1, number2, number3
O/P => Is the first number the smallest? ____


*/

//  Program to check if the first is the smallest of the 3 numbers.
import java.util.Scanner;
public class FirstSmallestOrNot{
	public static void main(String [] args){

	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the first Number: ");
	int number1 = input.nextInt();
	System.out.print("Enter the Second Number: ");
	int number2 = input.nextInt();
	System.out.print("Enter the Third Number: ");
	int number3 = input.nextInt();
	

	// Printing true or false, is the first Number is the smallest of 3 numbers
	System.out.println("Is the first number the smallest? " + ((number1 < number2) && (number1 < number3)) );

	input.close();


	}

}

