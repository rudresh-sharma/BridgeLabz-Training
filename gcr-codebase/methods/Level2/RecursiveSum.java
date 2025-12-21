/*

Write a program to find the sum of n natural numbers using recursive method and compare the result with the formulae n*(n+1)/2 and show the result from both computations is correct. 
Hint => 
Take the user input number and check whether it's a Natural number
Write a Method to find the sum of n natural numbers using recursion
Write a Method to find the sum of n natural numbers using the formulae n*(n+1)/2 
Compare the two results and print the result


*/
import java.util.Scanner;
public class RecursiveSum{
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int number = in.nextInt();
	int n = number;


	// Checking sum of n natural number calculated  is correct or not
		if(n<0){
			System.out.println("Given number is not a natural number.");
			System.exit(-1);
		}

	int sumByFormula =  n*(n+1)/2;
	int sumByRecursion = sumByRecursion(n);
		if(sumByFormula == sumByRecursion){
			System.out.println("Yes computation from both is correct.");
		}

	in.close();

	}


	// Recursive method to find the sum of n natural numbers
	public static int sumByRecursion(int n){
		if(n==0){
			return 0;
		}

		return n + sumByRecursion(n-1);
	}

}


 


