package com.daytwo.cabbygo;


import java.util.Scanner;
public class CabbygoApp {
	
	public static void main(String[] args) {
	
	Scanner in = new Scanner(System.in);
	Vehicle v = null;
	Driver d = null;
	int choice;
	do {
		System.out.println("Welcome to CABBY GO rides");
		System.out.println("1. Book a ride");
		System.out.println("2. End a ride");
		System.out.println("3. View driver details");
		System.out.println("4. View Vehicle details");
		System.out.println("5. Exit");

		choice = in.nextInt();
		in.nextLine();
		double distance;
		switch(choice) {
		
		case 1: {
			System.out.println("Enter vehice type");
			System.out.println("1. Mini");
			System.out.println("2. Sedan");
			System.out.println("3. SUV");
			int vType = in.nextInt();
			in.nextLine();
			
			
			if(vType == 1) {
				v = new Mini("MH01AB1234", 2);
				d = new Driver("Rahul kumar", "853432342", "32444442342", 4.1);
			}
			else if(vType == 2) {
				v = new Sedan("MH01AB1235", 2);
				d = new Driver("Sonu kumar", "853431142", "320004442342", 4.5);
			}
			else if(vType == 3) {
				v = new SUV("MH01AB1236", 2);
				d = new Driver("Rahul kumar", "853432342", "32444442342", 4.1);
			}
			
			
			System.out.print("\nEnter pickup location:");
			String pickL = in.next();
			in.nextLine();
			
			System.out.print("\nEnter drop location:");
			String dropL = in.next();
			in.nextLine();
			
			System.out.println("Enter the distance:");
			distance = in.nextDouble();
			
			
			d.setDistance(distance);
			d.setDropLocation(dropL);
			d.setPickUpLocation(pickL);
			
			v.bookRide();
			
			break;
			}
		
		case 2:
			if(v != null){
					v.endRide(d,v);
				}
				else {
					System.out.println("Sorry You did not booked any ride");
				}
				
				break;
				
		case 3:
			
			if(d != null)
				   d.showDriverDetails();
				else
				   System.out.println("No ride booked yet");
			break;
			
		case 4: 
			if(v != null)
				   v.showVehicleDetails();
				else
				   System.out.println("No ride booked yet");
			break;
				
		
		}
		
		
		
		
		
	}while(choice != 5);
	
	
	in.close();
	}	
	
}
