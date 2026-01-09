/*
 * 19. "Artify – Digital Art Marketplace"
Story: Artists sell digital art via Artify. Buyers can purchase, license, or subscribe to collections.
Requirements:
● Artwork class: title, artist, price, licenseType.
● User class: name, walletBalance.
● Interface IPurchasable with purchase() and license() methods.
● Encapsulation: licensing terms are protected.
● Use constructors to initialize artworks with or without previews.
● Operators: deduct wallet balance on purchase.
● Inheritance: DigitalArt, PrintArt from Artwork.
● Polymorphism: licensing varies across art types.
 */



package com.dayseven.artify;
import java.util.ArrayList;
import java.util.Scanner;

public class ArtifyApp {
	
	public static ArrayList<Artwork> arts = new ArrayList<>();
	public static Scanner in = new Scanner(System.in);
	
	
	public static void main(String[] args) {
			
		// Digital Arts
		Artwork art1 = new DigitalArt("D1", "Galaxy", "Rudresh", 500.0, "Commercial", "PNG", 25.5);
		Artwork art2 = new DigitalArt("D2", "Neon City", "Aarav", 750.0, "Personal", "JPG", 18.2);
		Artwork art3 = new DigitalArt("D3", "Cyber Dragon", "Meera", 1200.0, "Exclusive", "NFT", 50.0);

		// Print Arts
		Artwork art4 = new PrintArt("P1", "Sunset Print", "Neha", 1500.0, "Single Print", "Canvas", "24 x 36 inches");
		Artwork art5 = new PrintArt("P2", "Mountain View", "Rohit", 2000.0, "Multiple Prints", "Glossy Paper", "18 x 24 inches");
		Artwork art6 = new PrintArt("P3", "Abstract Lines", "Sana", 1800.0, "Display Only", "Mat Paper", "12 x 16 inches");
		
		
		User u = new User("Aanarkali", 20000.0);
		
		arts.add(art1);
		arts.add(art2);
		arts.add(art3);
		arts.add(art4);
		arts.add(art5);
		arts.add(art6);
		
		System.out.println("=====================================");
		System.out.println("    ARTIFY - Art MarketPlace");
		System.out.println("=====================================");
		
		
	
		
		int openChoice ;
		do {
			System.out.println("1. View All Artworks");
			System.out.println("2. View Artwork Details");
			System.out.println("3. Buy Artwork");
			System.out.println("4. License Artwork");
			System.out.println("5. View Wallet Balance");
			System.out.println("6. Exit");
			
			System.out.println("Enter choice number:");
			int choiceNo = in.nextInt();
			
			
			
			switch(choiceNo) {
			case 1: 
					ArtifyApp.viewAllArtworks();
					break;
					
			case 2: 
					System.out.println("Enter Artwork id");
					String id = in.next();
					
					
					for(Artwork a : arts) {
						if(a.getId().equals(id)) {
							 a.printArtDetails();
							 break;
							
						}
					}
					break;
			
			case 3: 
					System.out.println("Enter Artwork Id");
					String artId = in.next();
					
					for(Artwork a : arts) {
						if(a.getId().equals(artId)) {
							 a.purchase(u);
							 break;
						}
					}
					
					break;
					
					
					
			case 4: 
				System.out.println("Enter Artwork id");
				String ida = in.next();
				System.out.println("License Done!");
				
				for(Artwork a : arts) {
					if(a.getId().equals(ida)) {
						 
						if(a instanceof PrintArt) {
							a.license(u);
							break;
						}
						else if(a instanceof DigitalArt) {
							a.license(u);
							break;
						}
						 break;
					}
				}
				
				break;
				
				
			case 5: 
				System.out.println("Wallet Balance: " + u.getWalletBalance());
				break;
				
			
			case 6: 
					System.out.println("Thank you !!");
					break;
					
		    }
					
			System.out.println("Want a menu again(1 for yes or 0 for no)");
			openChoice = in.nextInt();
		}while(openChoice != 0);
	}
	
	
	
	public static void viewAllArtworks() {
		System.out.println("Following Artworkds are available");
		
		System.out.println("ID 	| Title | Artist | Price | Type\n");
		for(Artwork a : arts) {
			String type = null;
			
			if(a instanceof PrintArt) {
				type = "Print";
			}
			else if(a instanceof DigitalArt) {
				type = "Digital";
			}
			System.out.println(a.getId() + " | " + a.getTitle() + " | " + a.getArtist() + " | " + a.getPrice() + " | " + type);
			
			
			
		}
	}
	
}
