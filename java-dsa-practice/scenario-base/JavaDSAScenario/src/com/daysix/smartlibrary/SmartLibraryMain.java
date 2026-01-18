package com.daysix.smartlibrary;

import java.util.Scanner;

import com.daysix.artexpo.InsertionSort;

import java.util.ArrayList;
public class SmartLibraryMain {
	
	public static void main(String[] args) {
	
		Scanner in = new Scanner(System.in);
		
		ArrayList<Books> books = new ArrayList<>();		
		ArrayList<Books> userBooks = new ArrayList<>();
		
		  // 🔹 Creating 5 book objects
        books.add(new Books("Algorithms"));
        books.add(new Books("Computer Networks"));
        books.add(new Books("Database Systems"));
        books.add(new Books("Operating Systems"));
        books.add(new Books("Artificial Intelligence"));
		
		
        
        System.out.println("Available books\n");
		
        for(int i=0; i<books.size(); i++) {
        		System.out.println(i + "--> " + books.get(i));
        }
		
        
        System.out.println("Do you want to borrow books(yes/no)");
        String choice = "yes";
        
        do {
        		
        		System.out.println("Enter book index");
        		int index = in.nextInt();
        		userBooks.add(books.get(index));
        		
        		InsSort.insshort(userBooks, userBooks.size()-1);
        		
        		System.out.println("Do you want to borrow more book(yes/no)");
        		choice = in.next();
        	
        }while(choice.equalsIgnoreCase("yes"));
		
		
        
        
        System.out.println("\n\nSorted User boooks");
        
        for(Books b : userBooks) {
        		System.out.println(b);
        }
        
        
	}	
}
