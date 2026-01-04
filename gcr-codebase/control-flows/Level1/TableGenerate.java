/*

Create a program to find the multiplication table of a number entered by the user from 6 to 9.
Hint => 
Take integer input and store it in the variable number
Using a for loop, find the multiplication table of number from 6 to 9 and print it in the format number * i = ___ 

*/


import java.util.Scanner;
public class TableGenerate{
	public static void main(String[] args){

	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the any number from 6 to 9: ");
	int num = input.nextInt();

	
	//Printing the table
		for(int i=1; i<=10; i++){
			System.out.println(num + " * " + i + " = " + (num*i)); 
		}

	input.close();

	}


}