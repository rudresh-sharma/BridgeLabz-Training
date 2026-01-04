/*

Write a program to take 2 numbers and print their quotient and reminder
Hint => Use division operator (/) for quotient and moduli operator (%) for reminder
I/P => number1, number2
O/P => The Quotient is ___ and Reminder is ___ of two number ___ and ___

*/


import java.util.Scanner;


// Program to find the Quotient and reminder when two number divides
public class QuotientReminder{
	public static void main(String [] args){
	
	//Creating Scanner object to take input
	Scanner input = new Scanner(System.in);

	// --- input ---
	System.out.print("Enter the number 1: ");
	int num1 = input.nextInt();
	System.out.print("Enter the number 2: ");

	// -- logic/calculation
	int num2 = input.nextInt();
	int quotient = num1/num2;
	int reminder = num1%num2;
	
	// --- output
	System.out.println("The Quotient is " + quotient + " and Reminder is " + reminder + " of two number " + num1 + " and " + num2);

	}

}
