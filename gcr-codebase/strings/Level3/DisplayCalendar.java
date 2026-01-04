/*

Create a program to display a calendar for a given month and year. The program should take the month and year as input from the user and display the calendar for that month. E.g. for 07 2005 user input, the program should display the calendar as shown below

Hint => 
Write a Method to get the name of the month. For this define a month Array to store the names of the months
Write a Method to get the number of days in the month. For this define a days Array to store the number of days in each month. For Feb month, check for Leap Year to get the number of days. Also, define a Leap Year Method. 
Write a method to get the first day of the month using the Gregorian calendar algorithm
y0 = y − (14 − m) / 12
x = y0 + y0/4 − y0/100 + y0/400
m0 = m + 12 × ((14 − m) / 12) − 2
d0 = (d + x + 31m0 / 12) mod 7
Displaying the Calendar requires 2 for loops. 
The first for loop up to the first day to get the proper indentation. As in the example above 3 spaces from Sun to Thu as to be set as July 1st starts on Fri
The Second for loop Displays the days of the month starting from 1 to the number of days. Add proper indentation for single-digit days using %3d to display the integer right-justified in a field of width 3. Please note to move to the next line after Sat

*/


import java.util.Scanner;
public class DisplayCalendar{
	public static void main(String[] args){
	
	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the month: ");
	int m = in.nextInt();
	System.out.print("Enter the year: ");
	int y = in.nextInt();
	int d = 1;

	// Printing Calendar
	String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	int[] monthDays = {31,28,31,30,31,30,31,31,30,31,30,31};
	int y0 = (y - (14-m) /12);
	int x  = (y0 + (y0/4) - (y0/100) + (y0/400));
	int m0 = m + 12 * ((14-m) /12) - 2;
	int d0 = (d + x + (31*m0)/12)%7;	
		if(isLeapYear(y)){
			monthDays[1] = 29;
		}

	printsCalender(days, monthDays, d0,m);
	
	in.close();


	}

	//Method to check leapYear or not
	public static boolean isLeapYear(int year){
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	

// Method to print calendar of given month and year
public static void printsCalender(String[] days, int[] monthdays, int d0, int m){

 
    for(int i = 0; i < 7; i++){
        System.out.printf("%5s", days[i]);
    }
    System.out.println();

    int point1 = 0;

 
    while(point1 < d0){
        System.out.printf("%5s", "");
        point1++;
    }

 
    for(int day = 1; day <= monthdays[m-1]; day++){
        System.out.printf("%5d", day);
        point1++;

 
        if(point1 > 6){
            System.out.println();
            point1 = 0;
        }
    }

    System.out.println();
}

}

