package com.dayeight.movietime;

import java.time.LocalTime;

public class MovieData {
	
	private String title;
	private LocalTime time;
	
	public MovieData(String title, LocalTime time) {
		super();
		this.title = title;
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	
	
	
}
