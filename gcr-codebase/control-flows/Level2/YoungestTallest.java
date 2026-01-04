/*

Create a program to find the youngest friends among 3 Amar, Akbar, and Anthony based on their ages and the tallest among the friends based on their heights
Hint => 
Take user input for the age and height of the 3 friends and store it in a variable
Find the smallest of the 3 ages to find the youngest friend and display it
Find the largest of the 3 heights to find the tallest friend and display it

*/


import java.util.Scanner;
public class YoungestTallest{
	public static void main(String[] args){

	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the age of Amar: ");
	int ageAmar = input.nextInt();
	System.out.print("Enter the age of Akbar: ");
	int ageAkbar = input.nextInt();	
	System.out.print("Enter the age of Anthony: ");
	int ageAnthony = input.nextInt();
	System.out.print("Enter the height of Amar in cm: ");
	float heightAmar = input.nextFloat();	
	System.out.print("Enter the height of Akbar in cm: ");
	float heightAkbar = input.nextFloat();	
	System.out.print("Enter the height of Anthony in cm: ");
	float heightAnthony = input.nextFloat();


	// Finding youngest and tallest
	System.out.println();

	findingYoungest(ageAmar, ageAkbar, ageAnthony);	
	findingTallest(heightAmar, heightAkbar, heightAnthony);


	input.close();

	}


	// Method for finding the youngest among all three friends
	public static void findingYoungest(int ageAmar, int ageAkbar, int ageAnthony){
	
		if( (ageAmar<ageAkbar) && (ageAmar<ageAnthony)){
			System.out.println("Youngest friend is Amar with age " + ageAmar);
		}	
		else if((ageAkbar<ageAmar) && (ageAkbar<ageAnthony)){
			System.out.println("Youngest friend is Akbar with age " + ageAkbar);
		}
		else {
			System.out.println("Youngest friend is Anthony with age " + ageAnthony);

		}
	}


	// Method for finding the Tallest among all three friends
	public static void findingTallest(float heightAmar, float heightAkbar, float heightAnthony){
	
		if((heightAmar>heightAkbar) && (heightAmar>heightAnthony)){
			System.out.println("Tallest friend is Amar with height " + heightAmar + " cm ");
		}
		else if((heightAkbar>heightAnthony) && (heightAkbar>heightAmar)){
			System.out.println("Tallest friend is Akbar with height " + heightAkbar + " cm ");
		}
		else {
			System.out.println("Tallest friend is Anthony with height " + heightAnthony + " cm ");
		}

	}


}
		
		

















