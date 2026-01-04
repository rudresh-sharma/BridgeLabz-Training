/*

Write a program to check if a number is divisible by 5
I/P => number
O/P => Is the number ___ divisible by 5? ___


*/

// Program to to check if a number is divisible by 5
import java.util.Scanner;
public class DivisibleBy5{
	public static void main(String [] args){
	
	// Taking inputs 
	Scanner input = new Scanner(System.in);
	System.out.print("\n Enter the number : ");	
	int number = input.nextInt();
	

	// Printing true or false, Is number divisible by 5 or not	
	System.out.println("Is the number " + number + " divisible by 5? " +  (number % 5 == 0));

	input.close();

	}


}


	