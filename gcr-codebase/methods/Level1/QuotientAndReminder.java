/*

Write a program to take 2 numbers and print their quotient and reminder
Hint => 
Take user input as integer
Use division operator (/) for quotient and moduli operator (%) for reminder
Write Method to find the reminder and the quotient of a number 
public static int[] findRemainderAndQuotient(int number, int divisor) 


*/



import java.util.Scanner;
public class QuotientAndReminder{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number 1:  ");
	int num1 = in.nextInt();
	System.out.print("Enter the number 2: ");
	int num2 = in.nextInt();


	// Printing quotient and reminder

	int[] quotientAndReminder = findRemainderAndQuotient(num1, num2);

	System.out.println("Quotient = " + quotientAndReminder[0]);
	System.out.println("Reminder = " + quotientAndReminder[1]);

	in.close();


	}



	// Method for finding Quotient and Reminder
	public static int[] findRemainderAndQuotient(int number, int divisor) {
		int[] temp = new int[2];
		
		temp[0] = number/divisor;
		temp[1] = number%divisor;
		
		return temp;
	}


}
		









