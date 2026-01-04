/*

Write a Program to find the factorial of an integer entered by the user.
Hint => 
For example, the factorial of 4 is 1 * 2 * 3 * 4 which is 24.
Take an integer input from the user and assign it to the variable. Check the user has entered a positive integer.
Using a while loop, compute the factorial.
Print the factorial at the end.

*/


import java.util.Scanner;
public class FactorialOfNumWhile{
	public static void main(String [] args){

	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the number: ");
	long num = input.nextInt();
	long factorial = 1;
	
	// Calculating factorial of a given number
		if(num > 0) 
                {
			while(num>=1){
				factorial *= num;
				num -= 1;
			}
			System.out.println("Factorial of given Number is " + factorial );

		}
		else
			System.out.println("Number is not positive");


	//input.close();

	}

}
	



