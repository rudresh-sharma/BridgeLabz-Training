/*

Core Java Scenario Based Problem Statements
3. Election Booth Manager ️
Design a polling booth system.
● Take age input.
● Use if to check if eligible (>=18).
● Record vote (1, 2, or 3 for candidates).
● Loop for multiple voters, exit on special code.

*/
import java.util.Scanner;
public class ElectionBoothManager️{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	int c1 = 0, c2 = 0, c3 = 0;
	System.out.print("Enter your age: ");
	int age = in.nextInt();

		while(age != -1){

			if(age>=18){
				System.out.println(
				    "Vote for candidate:\n" +
				    "1. Candidate A\n" +
				    "2. Candidate B\n" +
				    "3. Candidate C"
					);


				int choice = in.nextInt();
				switch(choice){
					case 1: c1++;
						break;
					case 2: c2++;
						break;
					case 3: c3++;
						break;
					default : System.out.println("Restart You entered out of choice");
						break;
				}

				System.out.println("Thankyou for voting");
			}
			

			System.out.println("Enter correct age or write -1 for exit");
			age = in.nextInt();

		}



		System.out.print("Voting ended. \n " +
				"Candidate A:"+ c1 +"votes \n" +
				"Candidate B:"+ c2 +"votes \n "+
				"Candidate C:"+ c3 +"votes \n");


	in.close();
	}

}


