package com.constructors.levelone;

public class Person {

	private String name;
	private String dob;
	private String rollNumber;
	
	
	public Person(String name, String dob, String rollNumber) {
		this.name = name;
		this.dob = dob;
		this.rollNumber = rollNumber;
	}
	
	public Person(Person p) {
		this.name = p.name;
		this.dob = p.dob;
		this.rollNumber = p.rollNumber;
	}
	public String getData() {
		return name + " " + dob + " " + rollNumber;
	}
	
	
	
	
	
	
	
}
