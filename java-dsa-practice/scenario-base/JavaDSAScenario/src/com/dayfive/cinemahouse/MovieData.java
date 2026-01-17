package com.dayfive.cinemahouse;

public class MovieData {
	
	private String movieName;
    private int showTime; // minutes since midnight

    public MovieData(String movieName, int hours, int minutes) {
        this.movieName = movieName;
        this.showTime = hours * 60 + minutes;
    }

    public int getShowTime() {
        return showTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getFormattedTime() {
        int h = showTime / 60;
        int m = showTime % 60;
        return String.format("%02d:%02d", h, m);
    }

	
	
	
	
}
