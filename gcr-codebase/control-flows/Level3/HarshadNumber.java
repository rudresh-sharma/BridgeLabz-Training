/*

Create a program to check if a number taken from the user is a Harshad Number.
Hint => 
A Harshad number is an integer which is divisible by the sum of its digits. 
For example, 21 which is perfectly divided by 3 (sum of digits: 2 + 1).
Get an integer input for the number variable.
Create an integer variable sum with initial value 0.
Create a while loop to access each digit of the number.
Inside the loop, add each digit of the number to sum.
Check if the number is perfectly divisible by the sum.
If the number is divisible by the sum, print Harshad Number. Otherwise, print Not a Harshad Number.


*/



import java.util.Scanner;
public class HarshadNumber{
	public static void main(String[] args){
	
	// Taking inputs
	Scanner in =  new Scanner(System.in);
	System.out.print("Enter the numbner: ");
	int originalNum = in.nextInt();
	int num = originalNum;
	int sum = 0;

	// Cheking  numberis harshad or not
		while(num>0){
			int digit = num%10;		
			sum += digit;
			num /=10;
		}

		if(originalNum%sum == 0){
			System.out.print("Number " + originalNum + " is a Harshad Number ");
		}
		else {
			System.out.print("Number " + originalNum + " is not a Harshad Number ");
		}


	in.close();

	}

}