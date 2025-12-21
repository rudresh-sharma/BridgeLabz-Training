/*

Write a program that takes a year as input and outputs the Year is a Leap Year or not 
Hint => 
The LeapYear program only works for year >= 1582, corresponding to a year in the Gregorian calendar. 
Also Leap year is divisible by 4 and not divisible by 100 or divisible by 400
Write a method to check for Leap Year using the conditions a and b

*/

import java.util.Scanner;
public class LeapYear{
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the year: ");
	int year = in.nextInt();
	

	// Printing given year is leap year or not
	if(year<1582){
		System.out.println("Its is not a in range of Gregorian calendar. ");
		System.exit(-1);
	}
	
	boolean leapYear = checkingLeapYear(year);
		if(leapYear){
			System.out.println("It\'s a leap year");
		}
		else {
			System.out.println("It\'s not a leap year");
		}

	in.close();
	
	}


	// Method for finding year is leap or not
	public static boolean checkingLeapYear(int year){
		if((year%4 == 0 && year%100 !=0) || year%400 ==0){
			return true;
		}
		else{
			return false;
		}
	}

}



















