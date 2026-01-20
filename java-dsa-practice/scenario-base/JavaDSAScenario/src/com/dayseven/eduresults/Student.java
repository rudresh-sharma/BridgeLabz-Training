package com.dayseven.eduresults;

public class Student {
	private String enrollNo;
	private String district;
	private int score;
	
	public Student(String enrollNo, String district, int score) {
		super();
		this.enrollNo = enrollNo;
		this.district = district;
		this.score = score;
	}
	public String getEnrollNo() {
		return enrollNo;
	}
	public void setEnrollNo(String enrollNo) {
		this.enrollNo = enrollNo;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
	@Override
	public String toString() {
		return district + " | " + enrollNo + " | " + score;
	}
	
	
	
}
