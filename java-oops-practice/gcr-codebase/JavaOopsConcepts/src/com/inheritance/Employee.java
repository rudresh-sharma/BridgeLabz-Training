package com.inheritance;

public class Employee {
	private String name;
	private String id;
	private float salary;
	
	
	
	public Employee() {
		System.out.println("Employee constructor invoked");
	}
	
	public Employee(String name, String id, float salary) {
		this.name = name;
		this.id = id;
		this.salary = salary;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public String getId() {
		return id;
	}
	
	
	public float getSalary() {
		return salary;
	}
	
	public void displayDetails() {
		System.out.println("Employee Details");
		System.out.println("Name : " + name);
		System.out.println("id" + id);
		System.out.println("salary : "+ salary );

	}
}
