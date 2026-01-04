/*
Problem 2: Date Arithmetic Create a program that:
➢ Takes a date input and adds 7 days, 1 month, and 2 years to it.
➢ Then subtracts 3 weeks from the result.
Hint: Use LocalDate.plusDays(), plusMonths(), plusYears(), and
minusWeeks() methods.

*/


import java.time.LocalDate;
import java.util.Scanner;
public class DateArithmetic {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

 	// Taking inputs
        System.out.print("Enter a date (yyyy-mm-dd): ");
        String inputDate = in.nextLine();
        LocalDate date = LocalDate.parse(inputDate);
	
	// Printing manipulated dates
        System.out.println("\nOriginal Date: " + date);
        date = date.plusDays(7);
        System.out.println("After adding 7 days: " + date);
        date = date.plusMonths(1);
        System.out.println("After adding 1 month: " + date);
        date = date.plusYears(2);
        System.out.println("After adding 2 years: " + date);
        date = date.minusWeeks(3);
        System.out.println("After subtracting 3 weeks: " + date);

        in.close();
    }
}
