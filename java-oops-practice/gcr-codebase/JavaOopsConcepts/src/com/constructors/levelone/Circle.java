package com.constructors.levelone;

public class Circle {

	private float radius;
	
	
	// Default constructor
	public Circle() {
		this.radius = 1;
	}
	
	// Constructor chaining
	public Circle(float radius) {
		this();
		this.radius = radius;
	}
	
	
	// Get radius
	public float getRadius() {
		return this.radius;
	}
	
	
	// Get area
	public float area() {
		return (float)Math.PI * (radius) * (radius);
	}
	
	
	
	
}
