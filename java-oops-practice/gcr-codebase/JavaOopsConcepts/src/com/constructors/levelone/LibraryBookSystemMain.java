package com.constructors.levelone;

import java.util.Scanner;

public class LibraryBookSystemMain {
	public static void main(String[] args) {
		
		// Taking inputs
		Scanner input=new Scanner(System.in);
		System.out.print("Enter the books (Atomic Life,AFreedom to Live,The Art of Living): ");
		String name = input.nextLine();
		Book b = new Book();
		
		// Checking book available or not
		boolean bookAvail = b.isAvailable(name);
		if( bookAvail) {
			System.out.print(name + " is available");
		}
		else {
			System.out.println(name + " isn't available");
		}
	}
}
