package com.streamapi.doctoravailability;

import java.util.List;

public class Doctor {
	private String name;
	private String specialty;
	private List<String> workingDays;
	public Doctor(String name, String specialty, List<String> workingDays) {
		super();
		this.name = name;
		this.specialty = specialty;
		this.workingDays = workingDays;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public List<String> getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(List<String> workingDays) {
		this.workingDays = workingDays;
	}
	
	
	   @Override
	    public String toString() {
	        return "Doctor{name='" + name +
	                "', specialty='" + specialty +
	                "', workingDays=" + workingDays + "}";
	    }
	
}
