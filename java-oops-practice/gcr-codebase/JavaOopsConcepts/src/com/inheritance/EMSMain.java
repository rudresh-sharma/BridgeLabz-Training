package com.inheritance;

public class EMSMain {
	
	public static void main(String[] args) {
		
		Employee manager = new Manager("Sushant", "1234", 340332.0f, 2);
		
		Employee dev = new Developer("Rudresh", "3456", 20000.0f, "Java");
		
		Employee intern = new Intern("Sachin", "3212", 31234.0f, 2);
		
		
		manager.displayDetails();
        System.out.println();

		dev.displayDetails();
        System.out.println();

        
		intern.displayDetails();
        System.out.println();

		
		
		
	}
}
