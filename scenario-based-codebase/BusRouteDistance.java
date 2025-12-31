/*

. Bus Route Distance Tracker 🚌
Each stop adds distance.
● Ask if the passenger wants to get off at a stop.
● Use a while-loop with a total distance tracker.
● Exit on user confirmation.


*/


import  java.util.Scanner;
public class BusRouteDistance{
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.println("Bus started broom..broom....");
	int stop = 1, user = 1;
	float totalDistance = 0, distance = 0;
	int choice = 1;
		while(choice == 1){
			while(user == 1){
			System.out.print("Stop " + stop + "arrived, ");
			float randDistance = (int)(Math.random() * 91) + 10;
			System.out.println("Distance of Stop " + stop + " is " + randDistance);
	
			distance += randDistance;
			stop++;
			System.out.println("For next stop 1 || 0 for exit");
			user = in.nextInt();
			}

		System.out.println("Total distance traveled by user = " + distance);
		System.out.print("Enter 1 for continue or 0 for exit");
		user = 1; stop = 0;
		choice = in.nextInt();
		}
	in.close();

	}

}
	
	
		