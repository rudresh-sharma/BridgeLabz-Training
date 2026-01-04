/*

Write a program to check whether a number is positive, negative, or zero.
Hint => Get integer input from the user. Write a Method to return -1 for negative number, 1 for positive number and 0 if number is zero


*/



import java.util.Scanner;
public class NumberCheck{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int number = in.nextInt();


	//Printing 1 for positive, -1 negative, 0 for zero
	int result = numberCheck(number);
	System.out.println(result);


	in.close();

	}


	// Methods for checking number
	public static int numberCheck(int n){
		if(n>0)	
			return 1;
		else if(n<0)	
			return -1;	
		else
			return 0;
	}

}