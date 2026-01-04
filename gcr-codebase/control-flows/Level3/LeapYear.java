/*

Write a LeapYear program that takes a year as input and outputs the Year is a Leap Year or not a Leap Year. 
Hint => 
The LeapYear program only works for year >= 1582, corresponding to a year in the Gregorian calendar. So ensure to check for the same. 
Further, the Leap Year is a Year divisible by 4 and not 100 unless it is divisible by 400. E.g. 1800 is not a Leap Year and 2000 is a Leap Year.
Write code having multiple if else statements based on conditio
ns provided above and a second part having only one if statement and multiple logica


*/



import java.util.Scanner;
public class LeapYear{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the year: " );
	int year = in.nextInt();


	// Cheking given is leap year or not
		if(year<1582){
			System.out.print("Year should be >=1582 " );
		}

	        else if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			System.out.print("Year is a leap year\n" );
		}
		else {
			System.out.print("Year is not a leap year\n" );
		}



	in.close();


	}


}