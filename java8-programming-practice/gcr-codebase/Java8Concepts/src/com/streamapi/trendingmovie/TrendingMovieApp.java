package com.streamapi.trendingmovie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrendingMovieApp {
	public static void main(String[] args) {
		List<Movie> movies = new ArrayList<>();

        // Adding 10 Movie objects
        movies.add(new Movie("Inception", 8.8, "2010"));
        movies.add(new Movie("Interstellar", 9.0, "2014"));
        movies.add(new Movie("The Dark Knight", 9.0, "2008"));
        movies.add(new Movie("3 Idiots", 8.0, "2009"));
        movies.add(new Movie("Avengers: Endgame", 8.3, "2019"));
        movies.add(new Movie("Parasite", 8.1, "2019"));
        movies.add(new Movie("Joker", 6.6, "2019"));
        movies.add(new Movie("Dangal", 8.6, "2016"));
        movies.add(new Movie("Titanic", 7.1, "1997"));
        movies.add(new Movie("KGF", 8.9, "2018"));
        
        Comparator<Movie> comparator = Comparator.comparing(Movie::getRating).reversed().thenComparing(Movie::getReleaseYear);
        List<Movie> top5 = movies.stream().filter(m -> m.getRating() > 7).sorted(comparator).limit(5).collect(Collectors.toList());
        
        for(Movie m: top5) {
        		System.out.println(m);
        }
	}
}
