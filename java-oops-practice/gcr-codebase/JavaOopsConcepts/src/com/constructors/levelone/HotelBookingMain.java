package com.constructors.levelone;

public class HotelBookingMain {
	 public static void main(String[] args) {
		 
		 // Printing hotel booking details
		   HotelBooking b1 = new HotelBooking();
		   System.out.println(b1.getData());		   
		   HotelBooking b2 = new HotelBooking("Nishul", "Standard", 3);
		   System.out.println(b2.getData()); 		   
		   HotelBooking b3 = new HotelBooking(b1);
		   System.out.println(b3.getData());
	   }
		
}
