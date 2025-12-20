/*

An athlete runs in a triangular park with sides provided as input by the user in meters. If the athlete wants to complete a 5 km run, then how many rounds must the athlete complete
Hint => 
Take user input for 3 sides of a triangle 
The perimeter of a triangle is the addition of all sides and rounds is distance/perimeter
Write a Method to compute the number of rounds user needs to do to complete 5km run


*/



import java.util.Scanner;
public class TraingularPark{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the side 1(in meters):");
	int side1 = in.nextInt();
	System.out.print("Enter the side 2(in meters):");
	int side2= in.nextInt();	
	System.out.print("Enter the side 3(in meters):");
	int side3= in.nextInt();


	// Printing the number of rounds
	float numberOfRounds = (float)Math.ceil(numberOfRounds(side1, side2, side3));
	System.out.println("Number of rounds to complete 5km are : " + numberOfRounds);
	

	in.close();


	}



	// Method for finding number of rounds
	public static float numberOfRounds(int side1, int side2, int side3){
		return (5000.0f/(side1+side2+side3));

	}


}
  