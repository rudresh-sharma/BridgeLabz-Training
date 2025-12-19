/*

Rewrite program 1 to determine Leap Year with single if condition using logical and && and or || operators

*/



import java.util.Scanner;
public class LeapYearLogical{
	public static void main(String[] args){

	//
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the year: " );
	int year = in.nextInt();


	//

	        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			System.out.print("Year is a leap year\n" );
		}
		else {
			System.out.print("Year is not a leap year\n" );
		}



	in.close();


	}


}