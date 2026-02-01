package com.streamapi.filteringexpiringmemberships;

import java.time.LocalDate;

public class GymMemberShip {
	private String name;
	private LocalDate membershipDate;
	public GymMemberShip(String name, LocalDate membershipDate) {
		super();
		this.name = name;
		this.membershipDate = membershipDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getMembershipDate() {
		return membershipDate;
	}
	public void setMembershipDate(LocalDate membershipDate) {
		this.membershipDate = membershipDate;
	}
	
	
	@Override
	public String toString() {
		return "Name: " + name + "Date: " + membershipDate;
	}
}
