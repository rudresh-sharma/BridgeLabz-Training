package com.constructors.levelone;

public class HotelBooking {

	private String guestName;
	private String roomType;
	private int nights;
	
	
	public HotelBooking(){
		this.guestName = "Rudresh Sharma";
		this.roomType = "Deluxe";
		this.nights = 1;
	}
	
	public HotelBooking(String guestName, String roomType, int nights) {
		this.guestName = guestName;
		this.roomType = roomType;
		this.nights = nights;
	}
	
	
   public HotelBooking(HotelBooking b) {
	   this.guestName = b.guestName;
		this.roomType = b.roomType;
		this.nights = b.nights;
   }
   
   public String getData() {
	   return guestName + " " + roomType + " " + nights;
   }
   
   
  
}
