package com.dayeleven.addressbooksystem;
import java.io.Serializable;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L; 
	private String fName;
	private String lname;
	private String phoneNo;
	private String email;
	
	private Address address;
	
	
	public Contact(String fName, String lname, String phoneNo, String email, Address address) {
		super();
		this.fName = fName;
		this.lname = lname;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	public void printContactDetail() {
		System.out.println("First Name: " + fName);
		System.out.println("Last Name: " + lname);
		System.out.println("Phone Number: " + phoneNo);
		System.out.println("Email: " + email);
		System.out.println("City: " + address.getCity());
		System.out.println("State: " + address.getState());
		
		System.out.println("----------------------------------");
		System.out.println();
	}
	
	
}


class Address implements Serializable {
    private static final long serialVersionUID = 1L;

	private String city;
	private String state;
	
	
	
	public Address(String city, String state) {
		super();
		this.city = city;
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}