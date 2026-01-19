package com.dayten.geomeasure;

public class LineSegment extends Coordinate {
	
	private double lineSegment;
	
	public LineSegment(double x1, double x2, double y1, double y2) {
		super( x1, x2, y1, y2);
		length();
	}
	
	public void length() {
		
		double x = Math.pow(getX2()-getX1(), 2);
		double y = Math.pow(getY2()-getY1(), 2);
		
		lineSegment = Math.sqrt(x+y);
		
	}

	public double getLineSegment() {
		return lineSegment;
	}
	
	public static  void comparison(LineSegment a , LineSegment b) {
		
		if(a.lineSegment == b.lineSegment) {
			System.out.println("Both are equal.");
		}
		else if(a.lineSegment > b.lineSegment) {
			System.out.println("First length is longer.");
		}
		else{
			System.out.println("Second length is longer.");
		}
	}

}