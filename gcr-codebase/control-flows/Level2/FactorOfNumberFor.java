/*

Create a program to find the factors of a number taken as user input.
Hint => 
Get the input value for a variable named number and check if it is a positive integer.
Run a for loop from i = 1 to i < number. In each iteration of the loop, check if the number is perfectly divisible by i. If true, print the value of i.


*/


import java.util.Scanner;
public class FactorOfNumber{
	public static void main(String[] args){
	
	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int num = input.nextInt();
	
	
	// Printing factors
	System.out.println("Factors of " + num + " are");
		for(int i=1; i<num; i++){
			if(num%i == 0)
				System.out.println(i);
		}

	input.close();

	}
	
}
	