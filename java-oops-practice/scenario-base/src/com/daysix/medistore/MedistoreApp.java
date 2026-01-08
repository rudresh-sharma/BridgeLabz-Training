package com.daysix.medistore;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
public class MedistoreApp {
	
	public static Scanner in  = new Scanner(System.in);
	public static ArrayList<Medicine> inventory = new ArrayList<>();
	
	public static void main(String[] args) {
		
		System.out.println("Want to explore the system.");
		int choice = in.nextInt();
		ArrayList<Medicine> orderMed = new ArrayList<>();
		
		do {
			
			System.out.println("========================================\n");
			System.out.println("			Welcome to MediStore");
			System.out.println("   	Pharmacy Inventory & Sales System");
			System.out.println("========================================\n");
			
			System.out.println("1. Add New Medicine");
			System.out.println("2. View All Medicines");
			System.out.println("3. Sell Medicine");
			
			int menuChoice = in.nextInt();
			
			switch(menuChoice) {
			case 1:
					int addMedicine = 1;
					do {
						
						Medicine mi= null;
						
						System.out.print("Enter Medicine name: ");
						in.nextLine();
						String name = in.nextLine();
						
						System.out.print("Enter Price: ");
						double price = in.nextDouble();
						
						System.out.println("Enter Expiry date (YYYY-MM-DD)");
						String input = in.next();
						LocalDate date = LocalDate.parse(input);
						in.nextLine();
						System.out.println("Enter Stock: ");
						int stock = in.nextInt();
						
						
						
						System.out.println("Enter type of medicine");
						System.out.println("1. Tablet");
						System.out.println("2. Syrup");
						System.out.println("3. Injection");
						
						int medType = in.nextInt();
						
						if(medType == 1) {
							System.out.println("Enter number of Tablets Per strip");
							int noOfTabInStrip = in.nextInt();
							
							System.out.println("Enter Strength");
							String strength = in.nextLine();
							
							mi = new Tablet(name, price, date, stock, noOfTabInStrip, strength);
							in.nextLine();
							
							
						}
						else if(medType == 2) {
							System.out.println("Enter Volume of Bottle");
							double bottleVolume = in.nextDouble();
							
							mi = new Syrup(name, price, date, stock,bottleVolume);
						}
						
						else if(medType == 3) {
							System.out.println("Enter dose of injection");
							double dose = in.nextDouble();
							
							mi = new Injection(name, price, date, stock,dose);
						}
						
						inventory.add(mi);
						
						System.out.println("Want to add more medicine (1 for yes/ 0 for no)");
						addMedicine = in.nextInt();
					}while(addMedicine == 1);
				
					break;
					
			case 2: 
					System.out.println("What type of medicine you want to see");
					System.out.println("1. Tablet");
					System.out.println("2. Syrup");
					System.out.println("3. Injection");
					
					int typeMed = in.nextInt();
					in.nextLine();
					
					if(typeMed == 1) {
						for(Medicine md : inventory) {
							if(md instanceof Tablet) {
								Tablet tb = (Tablet) md;
								tb.showDetails();
							}
						}
					}
					else if(typeMed == 2) {
						for(Medicine md : inventory) {
							if(md instanceof Syrup) {
								Syrup tb = (Syrup) md;
								tb.showDetails();
							}
						}
					}
					else if(typeMed == 3) {
						for(Medicine md : inventory) {
							if(md instanceof Injection) {
								Injection tb = (Injection) md;
								tb.showDetails();
							}
						}
					}
					break;
					
					
			case 3:
					System.out.println("Enter Correct Medicine Name");
					in.nextLine();
					String name = in.nextLine();
					
					for(Medicine m : inventory) {
						if(m.getName().equals(name)) {
							if(m.checkExpiry()) {
								System.out.println("Sorry Medicine is Expired");
							}
							else {
								System.out.println("Enter Quantity");
								int quantity = in.nextInt();
								in.nextLine();
								
								m.setStock(m.getStock()-quantity);
								double priceWithDisc = m.sell(quantity, m);
								
								System.out.println("Total Price: " + (priceWithDisc * quantity));
								System.out.println("Thank you for shopping");
							}
						}
					}
					
					break;
			}
			
			
			
			
			
			
			
		}while(choice != 7);
		
		
	}
	
	
	
	
	
	
	
	
}
