/*

Create a program to print odd and even numbers between 1 to the number entered by the user.
Hint => 
Get an integer input from the user, assign to a variable number and check for Natural Number
Using a for loop, iterate from 1 to the number
In each iteration of the loop, print the number is odd or even number


*/

import java.util.Scanner;
public class OddEvenInNum{
	public static void main(String [] args){
	
	// Taking inputs
	Scanner input = new Scanner(System.in);	
	System.out.print("Enter the Number: ");
	int num = input.nextInt();
	System.out.println();
	
	// Printing odd and even numbers from 1 to num
	System.out.println("Even numbrs in 1 to "+ num + " are: ");
		for(int i=1; i<=num; i++){
			if(i%2 == 0){
				System.out.println(i);
			}
		}
	System.out.println();
	System.out.println("Odd numbrs in 1 to " +  num + " are: ");
		for(int i=1; i<=num; i++){
			if(i%2 != 0){
				System.out.println(i);
			}
		}


	input.close();

	}


}



