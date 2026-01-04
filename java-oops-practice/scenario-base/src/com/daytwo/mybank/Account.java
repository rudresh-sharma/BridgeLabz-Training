package com.daytwo.mybank;

public class Account implements ITransaction {
	
	private String name;
	private String phoneNo;
	private String dob;
	private String accountNumber;
	private double balance;
	
	
	// Constructor to initialize object
	public Account(String accountNumber, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	
	
	public Account(String name, String phoneNo, String dob, double balance, String accountNumber) {
		super();
		this.name = name;
		this.phoneNo = phoneNo;
		this.dob = dob;
		this.balance = balance;
		this.accountNumber = accountNumber;
	}



	// Getter and Setter
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	
	// Method to deposit money
	@Override
	public void deposit(double amount) {
		if(amount<=0) {
			System.out.println("Sorry only valid amount can be deposit");
		}
		else {
			balance += amount;
			System.out.println("Deposit successful, Balance = " + balance);
		}
		
	}

	// Method to withdraw money
	@Override
	public void withdraw(double amount) {
		if(amount>0 && amount<=balance) {
			balance -= amount;
			System.out.println("Withdrawn money" + amount + " Balance = " + balance);
		}
		
		else {
			System.out.println("Sorry can't withdraw money not in range");
		}
		
	}

	
	// Method to check account balance
	@Override
	public void checkBalance() {
		// TODO Auto-generated method stub
		
		System.out.println("Balance  = " + balance);
		
		
		
	}
	
	
	// Method to generate randomAccountNumber
	public static String generateAccountNumber() {
		
		String accNo = "MYBANK";
		
		long number = (long)(Math.random() * (99000000L - 10000000L + 1) + 10000000L);
		
		
		String temp = String.valueOf(number);
		String randAcc = accNo+temp;
		
		return randAcc;
	}
	
	
	// Method Show Details
	public void showDetails() {
		System.out.println("Name:" + name);
		System.out.println("phNO: " + phoneNo);
		System.out.println("DOB:" + dob);
		System.out.println("AccountNo: " + accountNumber);
		System.out.println("Balance: " + balance);
	}



	public void calculateInterest() {
		// TODO Auto-generated method stub
		
	}
	
	
}
