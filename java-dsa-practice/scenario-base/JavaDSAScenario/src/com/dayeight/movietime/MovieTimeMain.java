package com.dayeight.movietime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;



public class MovieTimeMain {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		
		ArrayList<MovieData> data = new ArrayList<>();		
		int isAdd = 1;
		int i=0;
			while(isAdd == 1) {
				
				System.out.println("Enter Movie Details:");
				System.out.println("Title:");
				String title = sc.nextLine();
				
				System.out.println("Time (HH:MM)");	
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:MM");
				LocalTime t =  LocalTime.parse(sc.nextLine(),formatter); 
				
				data.add(new MovieData(title, t));
				
				MovieInsertionSort.insertionsort(data, i);
				i++;
				System.out.println("Enter 1 to see sorted ");
				int isWant = sc.nextInt();
				sc.nextLine();
				if(isWant  == 1) {
					MovieInsertionSort.showDetails(data);
				}
				System.out.print("Want to add movie(1 for yes 0 for no)");
				
				isAdd = sc.nextInt();
				sc.nextLine();
			}
		
		
		
	}
		
}
