package com.collections.examples.insurancepolicymanagementusinghashmap;

import java.time.LocalDate;

public class Policy {

	private String policyNumber;
	private String policyholderName;
	private LocalDate expiryDate;
	private String coverageType;
	private double premiumAmount;

	public Policy(String policyNumber, String policyholderName, LocalDate expiryDate, String coverageType,
			double premiumAmount) {
		this.policyNumber = policyNumber;
		this.policyholderName = policyholderName;
		this.expiryDate = expiryDate;
		this.coverageType = coverageType;
		this.premiumAmount = premiumAmount;
	}

	// Getters
	public String getPolicyNumber() {
		return policyNumber;
	}

	public String getPolicyholderName() {
		return policyholderName;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public String getCoverageType() {
		return coverageType;
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	// good string representation
	@Override
	public String toString() {
		return String.format("%s | %s | %s | %s | ₹%.2f", policyNumber, policyholderName, expiryDate, coverageType,
				premiumAmount);
	}
}