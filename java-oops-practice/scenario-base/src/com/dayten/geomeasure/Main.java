package com.dayten.geomeasure;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		ArrayList<String> list = new ArrayList<>();
		
		System.out.println("How many times you want to compare lines?");
		int n = sc.nextInt();
		
		for(int i = 1 ; i<=n ; i++) {
			
			System.out.println("Enter the coordinate of 1st line: (x1, x2, x3 and x4");
			LineSegment line1 = new LineSegment(sc.nextDouble() ,sc.nextDouble(), sc.nextDouble(), sc.nextDouble() );
			
			System.out.println("Enter the coordinate of 2nd line: (x1, x2, x3 and x4");
			LineSegment line2 = new LineSegment(sc.nextDouble() ,sc.nextDouble(), sc.nextDouble(), sc.nextDouble() );
			
			LineSegment.comparison(line1, line2);
		}
		sc.close();
	}

}