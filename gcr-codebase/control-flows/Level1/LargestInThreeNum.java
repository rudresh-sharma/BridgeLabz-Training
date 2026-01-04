/*

Write a program to check if the first, second, or third number is the largest of the three.
I/P => number1, number2, number3
O/P => 
Is the first number the largest? ____
Is the second number the largest? ___
Is the third number the largest? ___


*/

// Program to check if the first, second, or third number is the largest of the three.
import java.util.Scanner;
public class LargestInThreeNum{
	public static void main(String [] args){
	
	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the number 1: ");
	int number1 = input.nextInt();
	System.out.print("Enter the number 2: ");
	int number2 = input.nextInt();
	System.out.print("Enter the number 3: ");
	int number3 = input.nextInt();
	

	// Printing whether the numbers is largest or not.
	System.out.println("Is the first number the largest? " + ((number1 > number2) && (number1 > number3)) );
	System.out.println("Is the Second number the largest? " + ((number2 > number1) && (number2 > number3)) );
	System.out.println("Is the Third number the largest? " + ((number3 > number1) && (number3 > number2)) );

	input.close();
	
	}

}
