/*
9. School Bus Attendance System 🚍
Track 10 students' presence.
● Use for-each loop on names.
● Ask "Present or Absent?"
● Print total present and absent counts.


*/

//created class named BusAttendance
import java.util.Scanner;
public class BusAttendance{
    public static void main(String[]args){
	    Scanner input = new Scanner(System.in);
        int presentCount = 0;
        int absentCount = 0;

        // Loop for 10 students
        for (int i = 1; i <= 10; i++) {
            System.out.print("Student " + i + " - Present or Absent? : ");
            String status = input.next();
            if (status.equals("Present")) {
                presentCount++;
            } 
			else if (status.equals("Absent")) {
                absentCount++;
            } 
			else {
                System.out.println("Invalid input!");
            }
        }
        System.out.println("Total Present : " + presentCount);
        System.out.println("Total Absent  : " + absentCount);
    }
}