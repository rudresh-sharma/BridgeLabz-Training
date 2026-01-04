/*

4. Problem 4: Date Comparison Write a program that:
➢ Takes two date inputs and compares them to check if the first date is before, after,
or the same as the second date.
Hint: Use isBefore(), isAfter(), and isEqual() methods from the LocalDate


*/


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DateComparison {
    public static void main(String[] args) {
	
	// Taking inputs
        Scanner in = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        System.out.print("Enter first date (yyyy MM dd): ");
        String date1Input = in.nextLine();
        System.out.print("Enter second date (yyyy MM dd): ");
        String date2Input = in.nextLine();


        // Comparing dates using LocalDate
        LocalDate date1 = LocalDate.parse(date1Input, formatter);
        LocalDate date2 = LocalDate.parse(date2Input, formatter);
        if (date1.isBefore(date2)) {
            System.out.println("\nFirst date is BEFORE second date");
        }
        else if (date1.isAfter(date2)) {
            System.out.println("First date is AFTER second date");
        }
        else if (date1.isEqual(date2)) {
            System.out.println("Both dates are the SAME");
        }

        in.close();
    }
}
