/*

Write a program to create a calculator using switch...case.
Hint => 
Create two double variables named first and second and a String variable named op.
Get input values for all variables.
The input for the operator can only be one of the four values: "+", "-", "*" or "/".
Run a for loop from i = 1 to i < number.
Based on the input value of the op, perform specific operations using the switch...case statement and print the result.
If op is +, perform addition between first and second; if it is -, perform subtraction and so on.
If op is neither of those 4 values, print Invalid Operator.

*/


import java.util.Scanner;
public class SwitchCalculator{
	public static void main(String[] args){
	
	// Taking inputs
	Scanner in =  new Scanner(System.in);
	System.out.print("Enter the number 1: ");
	double first = in.nextDouble();
	System.out.print("Enter the one operator:  ");
	char op = in.next().charAt(0);
	System.out.print("Enter the number 2: ");
	double second = in.nextDouble();
	

	// Calculating operation using switch
	switch(op){
		case '+' : 
			System.out.println("Addition of number " + first + " and " + second + " is " + (first+second));
			break;
		case '-' : 
			System.out.println("Subtraction of number " + first + " and " + second + " is " + (first-second));
			break;
		case '*' : 
			System.out.println("Multiplication of number " + first + " and " + second + " is " + (first*second));
			break;
		case '/' : 
			System.out.println("Division of number " + first + " and " + second + " is " + (first/second));
			break;
	}


	in.close();

	}

}
	