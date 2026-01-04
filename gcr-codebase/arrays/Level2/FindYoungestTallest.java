/*

Create a program to find the youngest friends among 3 Amar, Akbar, and Anthony based on their ages and the tallest among the friends based on their heights
Hint => 
Take user input for age and height for the 3 friends and store it in two arrays each to store the values for age and height of the 3 friends
Loop through the array and find the youngest of the 3 friends and the tallest of the 3 friends
Finally display the youngest and tallest of the 3 friends

*/


import java.util.Scanner;
public class FindingYoungestTallest{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the age in sequence of Amar, Akbar, Anthony: ");
	int[] ages = new int[3];
		for(int i=0; i<3; i++){
			ages[i] = in.nextInt();
		}
	System.out.print("Enter the heights in sequence of Amar, Akbar, Anthony: ");
	int[] heights = new int[3];
		for(int i=0; i<3; i++){
			heights[i] = in.nextInt();
		}
	
	// Calling method which will prints tallest and youngest
	findingYoungest(ages);
	findingTallest(heights);

	in.close();
	
	}

	// Method for finding the youngest among all three friends
	public static void findingYoungest(int [] ages){

		if( (ages[0] < ages[1]) && (ages[0] < ages[2])){
			System.out.println("Youngest friend is Amar with age " + ages[0]);
		}	
		else if((ages[1] < ages[0]) && (ages[1] < ages[2])){
			System.out.println("Youngest friend is Akbar with age " + ages[1]);
		}
		else {
			System.out.println("Youngest friend is Anthony with age " + ages[2]);

		}
	}

	// Method for finding the Tallest among all three friends
	public static void findingTallest(int [] heights){

		if( (heights[0] > heights[1]) && (heights[0] < heights[2])){
			System.out.println("Tallest friend is Amar with height " + heights[0] );
		}	
		else if((heights[1] > heights[0]) && (heights[1] > heights[2])){
			System.out.println("Tallest friend is Akbar with height " + heights[1] );
		}
		else {
			System.out.println("Tallest friend is Anthony with height " + heights[2]);
		}
	}

}





