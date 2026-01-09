package com.dayseven.artify;

public class User {
	private String name;
	private double walletBalance;
	
	
	
	
	
	public User(String name, double walletBalance) {
		super();
		this.name = name;
		this.walletBalance = walletBalance;
	}
	public User(String name2, int walletBalance2) {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWalletBalance() {
		return walletBalance;
	}
	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}
	
	
	
	
}
