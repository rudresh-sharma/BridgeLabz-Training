/*

Write an IntOperation program by taking a, b, and c as input values and print the following integer operations a + b *c, a * b + c, c + a / b, and a % b + c. Please also understand the precedence of the operators. 
Hint => 
Create variables a, b, and c of int data type.
Take user input for a, b, and c.
Compute 3 integer operations and assign the result to a variable
Finally, print the result and try to understand operator precedence.
I/P => fee, discountPrecent
O/P => The results of Int Operations are ___, ___, and ___


*/


import java.util.Scanner;

// Program for doing double Operations
public class DoubleOpt{
	public static void main(String [] args ){
	
	// Creating Scanner object for input
	Scanner input = new Scanner(System.in);

	// --- input ---
	System.out.print("Enter a : ");
	double a = input.nextDouble();
	System.out.print("Enter b : ");
	double b = input.nextDouble();
	System.out.print("Enter c : ");
	double c = input.nextDouble();

	// --- logic----

	// Storing integer operation results in different varible 
	double operation1 = a + b *c;
	double operation2 = a * b + c;
	double operation3 = c + a / b;
	double operation4 = a % b + c;
	
	// --- output ---
	System.out.print("The results of Int Operations are " + operation1 + " , " + operation2 + " , " + operation3 + " and " + operation4);


	}

}


	