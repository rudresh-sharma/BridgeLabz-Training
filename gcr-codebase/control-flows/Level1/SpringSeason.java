/*

Write a program SpringSeason that takes two int values month and day from the command line and prints “Its a Spring Season” otherwise prints “Not a Spring Season”. 
Hint => 
Spring Season is from March 20 to June 20


*/


import java.util.Scanner;

// Program to find whether there is spring season or not
public class SpringSeason{
	public static void main(String [] args){
	
	// Taking inputs
	Scanner input = new Scanner(System.in);
	int month = Integer.parseInt(args[0]);
	int day = Integer.parseInt(args[1]);

	//  Checking conditions if true then SpringSeason
		if((month ==3) && (day>=20 && day <=31)){
			System.out.println("Its a Spring Season");

		}

		else if ((month == 4 && day>=1 && day<=30) || (month == 5 && day>=1 && day<=31)){
			System.out.print("Its a Spring Season");
		}
			
		else if((month == 6) && (day>=1 && day<=20)){
			System.out.println("Its a Spring Season");

		}
		else {
			System.out.println("Its not a Spring Season");
		}	
		
	

	input.close();


	}

}
	

	