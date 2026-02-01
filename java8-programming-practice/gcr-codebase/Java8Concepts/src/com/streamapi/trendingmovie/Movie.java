package com.streamapi.trendingmovie;

public class Movie {
	private String name;
	private double rating;
	private String releaseYear;
	
	
	
	
	
	public Movie(String name, double rating, String releaseYear) {
		super();
		this.name = name;
		this.rating = rating;
		this.releaseYear = releaseYear;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	
	@Override
    public String toString() {
        return "Movie{name='" + name + 
               "', rating='" + rating + 
               "', releaseYear='" + releaseYear + "'}";
    }

//	@Override
//	public int compareTo(Movie o) {
//		int a = Integer.valueOf(this.getRating());
//		int b = Integer.valueOf(o.getRating());
//		return a-b;
//	}

}
