/*

15. Rohan’s Library Reminder App 📚
Rohan wants a fine calculator:
● Input return date and due date.
● If returned late, calculate fine: ₹5/day.
● Repeat for 5 books using for-loop.

*/


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
public class  LibraryReminderApp{
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);



		// Printing dues on bases difference bw due date and return date
		for(int i=0; i<5; i++){
		
		System.out.println("Book Number " + (i+1) + ": ");
		System.out.print("Enter due date in format(mm-dd-yyyy): ");
		String dueDate = in.next();


		System.out.print("Enter return date in format(mm-dd-yyyy): ");
		String returnDate = in.next();

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		 LocalDate d1 = LocalDate.parse(dueDate, formatter);
 	         LocalDate d2 = LocalDate.parse(returnDate, formatter);
		 long days = ChronoUnit.DAYS.between(d1, d2);


		if(days>0){
			long fine = 5*days;
			System.out.println("Your fine is: " + fine);
		}
		else{
			System.out.println("No fine needed");
		}
		
		}	

		in.close();

		
	}
}