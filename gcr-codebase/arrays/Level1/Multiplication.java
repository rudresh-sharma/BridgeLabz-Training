/*

Create a program to print a multiplication table of a number.
Hint => 
Get an integer input and store it in the number variable. Also, define a integer array to store the results of multiplication from 1 to 1
Run a loop from 1 to 10 and store the results in the multiplication table array
Finally, display the result from the array in the format number * i = ___

*/


import java.util.Scanner;
public class Multiplication{
	public static void main(String[] args){


	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int number = in.nextInt();
	int[] table = new int[10];


	// Generating table and printing
		for(int i=1; i<=10; i++){
			table[i-1] = number*i;
		}

		for(int i=0; i<10; i++){
			System.out.println(number + " * " + (i+1) + " = " + table[i]);
		}


	in.close();

	}

}