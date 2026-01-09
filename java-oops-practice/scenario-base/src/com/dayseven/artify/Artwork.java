package com.dayseven.artify;

public class Artwork implements IPurchasable{
	
	private String id;
	private String title;
	private String artist;
	private double price;
	private String licenseType;
	
	
	
	
	public Artwork(String id,String title, String artist, double price, String licenseType) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.price = price;
		this.licenseType = licenseType;
	}
	
	
	// Getter and Setters

	
	
	
	
	


	// Interface Methods
	@Override
	public void purchase(User u) {
		
		if(u.getWalletBalance()>= this.getPrice()) {
			System.out.println("Congrats Artwork is yours");
			System.out.println("Total Bill = " + this.getPrice());
			
			u.setWalletBalance(u.getWalletBalance()-this.getPrice());
		}
		
		else {
			System.out.println("Sorry You dont have enough balance");
		}
		
	}
	
	@Override
    public void license(User user) {
        System.out.println("Artwork licensed: " + title + " by " + artist);
    }
	
	
	// getter setters
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getLicenseType() {
		return licenseType;
	}


	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}


	
	
	// Method to print Artwork details
	public void printArtDetails() {
		System.out.println("Title: " + this.getTitle());
		System.out.println("Artist: " + this.getArtist());
		System.out.println("Price" + this.getPrice());
		System.out.println("License Type: " + this.getLicenseType());
		
		
		
	}



	
	
	
	
	
	
}
