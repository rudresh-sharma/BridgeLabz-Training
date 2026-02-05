package com.streamapi.employeestreamscenario;

import java.time.LocalDate;

public class Employee {

	private String id;
	private String name;
	private int age;
	private String gender;
	private String department;
	private LocalDate yearOfJoining;
	private double salary;
	
	
	public Employee(String id, String name, int age, String gender, String department, LocalDate yearOfJoining,
			double salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.department = department;
		this.yearOfJoining = yearOfJoining;
		this.salary = salary;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public LocalDate getYearOfJoining() {
		return yearOfJoining;
	}


	public void setYearOfJoining(LocalDate yearOfJoining) {
		this.yearOfJoining = yearOfJoining;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	@Override
	public String toString() {
		return 	"ID: " + id +
				"\nName: " + name +
				"\nAge: " + age +
				"\nGender: " + gender +
				"\nDepartment: " + department +
				"\nYOJ: " + yearOfJoining.toString() +
				"\nSalary: " + salary
				;
			
	}
	
	
	
	
	
	
	
}
