package com.inheritance;

public class Intern extends Employee{
	private int duration;
	
	public Intern() {
 	}
	
	public Intern(String name, String id, float salary, int duration) {
		super(name, id, salary);
		this.duration = duration;
	}
	
	
	@Override
	public void displayDetails() {
		super.displayDetails();
		System.out.println("Duration: "+ duration);

	}
}
