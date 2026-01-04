package com.constructors.levelone;

public class PersonMain {
	public static void main(String[] args) {
		Person p1 = new Person("Rudresh Sharma", "07-09-2025", "0191AL221147");
		Person p2 = new Person(p1);
		
		
		System.out.println(p2.getData());
		
	}
}
