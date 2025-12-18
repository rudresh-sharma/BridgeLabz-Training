/*

Create a program to find the bonus of employees based on their years of service.
Hint => 
Zara decided to give a bonus of 5% to employees whose year of service is more than 5 years.
Take salary and year of service in the year as input.
Print the bonus amount

*/


import java.util.Scanner;
public class PrintBonusAmount{
	public static void main(String [] args){
	
	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the salary: ");
	float salary = input.nextFloat();
	System.out.print("Enter the year of service: ");
	float yearOfService = input.nextFloat();
	float bonus = 0;
	
	// Calculating the bonus based on year of service
		if(yearOfService>5){
			bonus = (salary * 5 )/100;
			System.out.println("Bonus amount = " + bonus );
		}
		else {
			System.out.println("You are not eligible for bonus ");
		}


	input.close();

	}

}

	

