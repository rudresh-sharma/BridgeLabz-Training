/*

Write a program SpringSeason that takes two int values month and day from the command line and prints “Its a Spring Season” otherwise prints “Not a Spring Season”. 
Hint => Spring Season is from March 20 to June 20. Write a Method to check for Spring season and return a boolean true or false 


*/




import java.util.Scanner;
public class SpringSeason{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	int month = Integer.parseInt(args[0]);
	int day = Integer.parseInt(args[1]);


	// printing true or false on spring season
	boolean isSpringSeason = isSpring(month, day);
	System.out.println("Spring Season = " + isSpringSeason);


	in.close();

	}

	
	// Cheking springseason or not
	public static boolean isSpring(int month, int day){
	
		if((month ==3) && (day>=20 && day <=31)){
			return true;

		}

		else if ((month == 4 && day>=1 && day<=30) || (month == 5 && day>=1 && day<=31)){
			return true;
		}
			
		else if((month == 6) && (day>=1 && day<=20)){
			return true;

		}
		else {
			return false;
		}	


	}

}

	