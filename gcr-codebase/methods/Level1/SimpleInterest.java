/*

Write a program to input the Principal, Rate, and Time values and calculate Simple Interest.
Hint => 
Simple Interest = Principal * Rate * Time / 100
Take user input for principal, rate, time
Write a method to calculate the simple interest given principle, rate and time as parameters
Output “The Simple Interest is ___ for Principal ___, Rate of Interest ___ and Time ___”


*/


import java.util.Scanner;
public class SimpleInterest{
	public static void main(String[] args){

	// Taking inputs
	Scanner in =  new Scanner(System.in);
	System.out.print("Enter the Principal amount: " );
	float principal = in.nextFloat();
	System.out.print("Enter the rate: " );
	float rate = in.nextFloat();	
	System.out.print("Enter the time " );
	float time = in.nextFloat();

	// Printing simple Interest for given values
	float simpleInterest = findingSimpleInterest(principal, rate, time);
	System.out.println("The Simple Interest is " + simpleInterest+ " for Principal " + principal + ", Rate of Interest " + rate + " and Time " + time);
	

	in.close();

	}



	// Method for finding simple Interest
	public static float findingSimpleInterest(float principal, float rate, float time){
		return (principal*rate*time)/100;

	}

}
