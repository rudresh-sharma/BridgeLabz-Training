/*

Create a program to count the number of digits in an integer.
Hint => 
Get an integer input for the number variable.
Create an integer variable count with value 0.
Use a loop to iterate until number is not equal to 0.
Remove the last digit from number in each iteration
Increase count by 1 in each iteration.
Finally display the count to show the number of digits

*/


import java.util.Scanner;
public class CountDigits{
	public static void main(String[] args){

	// Taking inputs
	Scanner in =  new Scanner(System.in);
	System.out.print("Enter the number: ");
	int originalNum = in.nextInt();
	int number = originalNum;


	// Counting digits
	int countD = 0;
		while(number>0){
			int digit = number%10;		
			countD++;
			number /=10;
		}


	System.out.println("Number of digits in " + originalNum + " is " + countD );

	in.close();

	}

}
