package com.daysix.smartlibrary;

public class Books {
	private String title;

	
	
	
	
	public Books(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	@Override
	
	public String toString() {
		return title;
	}
	
}
