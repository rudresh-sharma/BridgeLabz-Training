/*

Create a program to find the shortest, tallest, and mean height of players present in a football team.
Hint => 
The formula to calculate the mean is: mean = sum of all elements/number of elements
Create an int array named heights of size 11 and get 3 digits random height in cms for each player in the range 150 cms to 250 cms
Write the method to Find the sum of all the elements present in the array.
Write the method to find the mean height of the players on the football team
Write the method to find the shortest height of the players on the football team 
Write the method to find the tallest height of the players on the football team
Finally display the results


*/



import java.util.Scanner;
public class HeightOfPlayers{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter number of Players in team ");
	int size = in.nextInt();
	float[] heights = new float[size];
		for(int i=0; i<size; i++){
			System.out.print("Enter the height of player " + (i+1) + " : "  );
			heights[i] = in.nextFloat();
		}

	

	// Printing shortest, tallest, and mean height of players
	float sum = sum(heights);
	float meanHeight = meanHeight(sum, size);
	int shortest = getShortest(heights);
	int tallest = getTallest(heights);
	System.out.println("Sum of heights = " + sum);
	System.out.println("Mean of heights = " + meanHeight);
	System.out.println("Shortest of all players is player "+ (shortest+1) + " with height = "  + heights[shortest]);
	System.out.println("Tallest of all players is player "+ (tallest+1) + " with height = "  + heights[tallest]);


	in.close();

	}



	// Method to find the sum of heights 
	public static float sum(float[] heights){
		float sum = 0;
		for(int i=0; i<heights.length; i++){
			sum += heights[i];
		}

		return sum;
	}


	// Method to find the mean height 
	public static float meanHeight(float sum, int size){
		 

		return sum/size;
	}


	// Method for finding shortest height with player
	public static int getShortest(float[] heights){
		float min = heights[0];
		int k=0;
		for(int i=1; i<heights.length; i++){
			if(heights[i]<min){
			    min = heights[i];
				k = i;
			}
		}

		return k;
	}


	// Method for finding shortest height with player
	public static int getTallest(float[] heights){
		float max = heights[0];
		int k = 0;
		for(int i=1; i<heights.length; i++){
			if(heights[i]>max){
				max = heights[i];
				k=i;
			}
		}

		return k;
	}




}


