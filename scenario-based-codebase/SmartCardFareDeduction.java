/*

4. Metro Smart Card Fare Deduction 🚇
In Delhi Metro, fare varies by distance.
● Ask the user for distance.
● Calculate fare using ternary operator.
● Deduct from smart card balance.
Loop until balance is exhausted or the user quits.

*/

import java.util.Scanner;
public class SmartCardFareDeduction{
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);
	int balance = 500;
	int totalFair = 0, totalDistanceTraveled = 0;
	System.out.print("Enter distance or quit(-1)" );
	int distance = in.nextInt();

	while(balance>0 && distance != -1){
		
		if(distance>0){
			int fair = distance * 2;
			balance -= fair;
			totalFair += fair;
			totalDistanceTraveled += distance;
			System.out.println("Your fair is "+ fair + "& Available balance = " + balance);
		}
		else{
			System.out.print("Enter correct distance: ");
			continue;
		}
		System.out.print("Enter distance or quit(-1) ");
		distance =  in.nextInt();
	}

	System.out.println("Total Fair is " + totalFair + ", Total Distance traveled = " + totalDistanceTraveled + " and Remaining balance = " + balance);

	}

}

		