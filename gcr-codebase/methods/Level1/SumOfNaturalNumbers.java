/*

Write a program to find the sum of n natural numbers using loop
Hint => Get integer input from the user. Write a Method to find the sum of n natural numbers using loop 


*/




import java.util.Scanner;
public class SumOfNaturalNumbers{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int number = in.nextInt();

	// Printing n natural Numbers sum
	int sum = sum(number);
	System.out.println("Sum of " + number + " natural numbeer is " + sum);


	in.close();

	}


	// Method for finding the sum of n natural numbers
	public static int sum(int n){
		int sum = 0;	
		for(int i=1; i<=n; i++)
			sum += i;

		return sum;

	}


}

	