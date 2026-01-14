package com.daythree.parceltracker;

import java.util.Scanner;
public class MainApp {

	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		StagesSLL obj = new StagesSLL();
		
		obj.add("Packed");
		obj.add("Shipped");
		obj.add("In Transit");
		obj.add("Delivered");
		

		
		System.out.println("Enter your choice.");
		int menuAgain = 1;
		do {
			System.out.println("====== ParcelTracker Menu");
			System.out.println("1. Show Parcel Stages");
			System.out.println("2. Move to next stages");
			System.out.println("3. Add Checkpoint");
			System.out.println("4. Mark as Lost");
			System.out.println("5. Exit");
			
			System.out.println("Enter your choice");
			int choice =  in.nextInt();
			
			
			switch(choice) {
			case 1: 
				obj.displayParcelStages();
				break;
				
			case 2:
				obj.moveNextStage();
				break;
				
			case 3: 
				System.out.println("Enter checkpoint");
				in.nextLine();
				String check = in.nextLine();
				
				obj.addCheckPoint(check);
				break;
				
			case 4:
				obj.markAsLost();
				break;
				
			case 5:
				System.out.println("Thank you  for tracking parcel!");
				System.exit(0);
			}
			
			System.out.println("Want a menu again! (1 or 0)");
			menuAgain = in.nextInt();
			
		}while(menuAgain == 1);
		
		
		
		
		
	}
	
	
	
}
