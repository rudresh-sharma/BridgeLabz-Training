package com.inheritance;

public class Manager extends Employee {
	
	private int teamSize;
	
	public Manager() {
	}
	
	public Manager(String name, String id, float salary, int teamSize) {
		super(name, id, salary);
		this.teamSize = teamSize;

	}
	@Override
	public void displayDetails() {
        super.displayDetails();
		System.out.println("Team size: "+ teamSize);

	}
	
	
	
	
}
