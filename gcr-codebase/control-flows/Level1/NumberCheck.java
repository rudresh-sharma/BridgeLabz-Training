/*

Write a program to check whether a number is positive, negative, or zero.
Hint => 
Get integer input from the user and store it in the number variable.
If the number is positive, print positive.
If the number is negative, print negative.
If the number is zero, print zero. 

*/


import java.util.Scanner;


//Program to check whether a number is positive, negative, or zero.
public class NumberCheck{
	public static void main(String [] args){
	
	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the number : ");
	int number = input.nextInt();

	// Is number Zero
		if(number == 0 ){
			System.out.println("zero");
		}
		
	// Is number Positive
		else if (number > 0){
			System.out.println("Positive");
		}
	
	// Is number Negative
		else {
			System.out.println("Negative");
		}


	input.close();


	}

}
