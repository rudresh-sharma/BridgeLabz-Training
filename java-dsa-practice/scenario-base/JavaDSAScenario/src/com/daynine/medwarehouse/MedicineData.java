package com.daynine.medwarehouse;

import java.time.LocalDate;

public class MedicineData {
	private String medName;
	private LocalDate expiryDate;

	public MedicineData(String medName, LocalDate expiryDate) {
		super();
		this.medName = medName;
		this.expiryDate = expiryDate;
	}
	public String getMedName() {
		return medName;
	}
	public void setMedName(String medName) {
		this.medName = medName;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	
	
}
