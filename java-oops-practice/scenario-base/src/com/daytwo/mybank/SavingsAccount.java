package com.daytwo.mybank;

public class SavingsAccount extends Account{
	
	private double interestRate;

	public SavingsAccount(String accountNumber, double initDepo, double interestRate) {
		super(accountNumber, initDepo);
		this.interestRate = interestRate;
	}
	
	
	
	public SavingsAccount(String name, String phoneNumber, String dob, double initDepo, String accNo) {
		
		super(name, phoneNumber, dob, initDepo, accNo);
	}



	// Method to calculate interest
	public void calculateInterest() {
		double interest = getBalance()*interestRate/100;
		
		System.out.println("Interest earned =" + interest);
		deposit(interest);
		
		System.out.println("Total balance after interest = " + getBalance());
		
	}

}
