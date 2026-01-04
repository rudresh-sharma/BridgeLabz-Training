/*

Create a program to find all the multiples of a number taken as user input below 100.
Hint => 
Get the input value for a variable named number. Check the number is a positive integer and less than 100.
Run a for loop backward: from i = 100 to i = 1.
Inside the loop, check if i perfectly divide the number. If true, print the number and continue the loop.

*/


import java.util.Scanner;
public class MultiplesFor{
	public static void main(String[] args){
	
	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int num = in.nextInt();


	// Printing all multiples
	System.out.println("Multiples of " + num + " below 100 are");

		for(int mul = num; mul<100; mul += num){
			System.out.println(mul);
		}

	in.close();

	}

}







