/*

16. Digital Watch Simulation ⏱️
Simulate a 24-hour watch:
● Print hours and minutes in a nested for-loop.
● Use a break to stop at 13:00 manually (simulate power cut).
Core Java Scenario Based Problem Statements

*/


import  java.util.Scanner;
public class DigitalWatchSimulation{
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);
	
	

	// Printing clock till 13:00
	for(int h=0; h<24; h++){
		for(int m=0; m<60; m++){
			if(h==13){
			System.out.printf("%02d : %02d%n",h,m);
			System.out.println("Sorry power cut");
			System.exit(-1);
			}
			System.out.printf("%02d : %02d%n",h,m);

		}
	}


	in.close();

	}
}






