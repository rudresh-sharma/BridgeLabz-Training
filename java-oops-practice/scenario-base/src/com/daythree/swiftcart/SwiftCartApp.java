package com.daythree.swiftcart;

import java.util.Scanner;
public class SwiftCartApp {
	
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		int menuChoice ;
		
		do {
			System.out.println(" 	Swift Cart	");
			System.out.println("==================");
			System.out.println("1. Add a user");
			System.out.println("2. Exit");
			
			menuChoice = in.nextInt();
			
			
			switch(menuChoice) {
			case 1:
				Cart c = new Cart();
				int choice;
				do {
				System.out.println("Enter your choice ");
				System.out.println("1. Create a cart.");
				System.out.println("2. View a cart.");
				System.out.println("3. Printing Invoice");
				System.out.println("4. Exit from current user");
				 choice = in.nextInt();
				in.nextLine();
				
				switch(choice) {
				case 1:
					int i=0, moreProduct = 1;
					while(moreProduct == 1) {
						System.out.println("Enter product " + (i+1)+ " details\n");
						System.out.print("name: ");
						String name = in.nextLine();
						
						System.out.print("price: ");
						double price = in.nextDouble();
						in.nextLine();
						
						System.out.print("quantity: ");
						int quantity = in.nextInt();
						in.nextLine();
						
						System.out.print("category: ");
						String category = in.nextLine();
						
						System.out.println("product type(1. perishable or 2. non-perishable: ");
						int productType = in.nextInt(); 
						Product p = new Product(name, price, category, quantity, productType);
						c.products.add(p);
						i++;
						System.out.println("Enter 1 for adding more or 0 for stop adding");
						moreProduct = in.nextInt();
						in.nextLine();
						
					}
						
						break;
						
						
				case 2: 
					int j=0;
					
					for(Product p : c.products) {
						System.out.println((j+1) + ".  Name: " + p.getName() + " || Unit Price: " + p.getPrice() + " || Quantity: " + p.getQuantity() + " || " + "UnitTotalPrice :"  + (p.getPrice() * p.getQuantity()) );                       
						j++;
					}
	 					
					break;
					
				case 3:
						c.generateBill(c);
						break;
				}
				}while(choice != 4);
				
				
			case 2:
					System.out.println("Thankyou for working with swift card");
					System.out.println("Number of User today comes - " + Cart.user );
					break;
					
			}
			
		}while(menuChoice != 2);
				
				
		
			
		}
	}

