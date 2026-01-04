package com.inheritance;

public class Developer extends Employee {
	
	private String programmingLanguage;
	
	public Developer() {
	}
	
	public Developer(String name, String id, float salary, String pgLanguage) {
		super(name, id, salary);
		this.programmingLanguage = pgLanguage;

	}
	
	@Override
	public void displayDetails() {
        super.displayDetails();
		System.out.println("Programming Language: "+ programmingLanguage);

	}
}
