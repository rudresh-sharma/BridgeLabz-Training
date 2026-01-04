package com.daytwo.mybank;

public class CurrentAccount extends Account{

	public CurrentAccount(String accountNumber, double balance) {
		super(accountNumber, balance);
	}
	
	
	public CurrentAccount(String name, String phoneNumber, String dob, double initDepo, String accNo) {
		
		super(name, phoneNumber, dob, initDepo, accNo);
	}

	
	

}
