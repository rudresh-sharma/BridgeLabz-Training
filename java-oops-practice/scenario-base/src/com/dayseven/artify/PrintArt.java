package com.dayseven.artify;

public class PrintArt extends Artwork{
	
	private String paperType;
	private String dimenstions;
	
	
	public PrintArt(String id,String title, String artist, double price, String licenseType, String paperType,String dimenstions) {
		super(id,title, artist, price, licenseType);
		this.paperType = paperType;
		this.dimenstions = dimenstions;
	}
	
	
	
	// getter and setters
	public String getPaperType() {
		return paperType;
	}
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}
	public String getDimenstions() {
		return dimenstions;
	}
	public void setDimenstions(String dimenstions) {
		this.dimenstions = dimenstions;
	}
	
	
	
	// Method to print Artwork details
	@Override
	public void printArtDetails() {
		super.printArtDetails();
		
		System.out.println("PaperType: " + this.getPaperType());
		System.out.println("Dimensions: " + this.getDimenstions());
		
		
	}
	
	
	@Override
	public void license(User user) {
	    System.out.println("\n--- Print Art Licensing ---");
	    System.out.println("Artwork: " + getTitle());
	    System.out.println("Artist: " + getArtist());
	    System.out.println("License Type: " + getLicenseType());

	    switch (getLicenseType()) {
	        case "Single Print":
	            System.out.println("✔ You can print one physical copy only.");
	            break;
	        case "Multiple Prints":
	            System.out.println("✔ You can print multiple copies for personal/display use.");
	            break;
	        case "Display Only":
	            System.out.println("✔ You can display it on walls but cannot print additional copies.");
	            break;
	        case "Resale Allowed":
	            System.out.println("✔ You can resell this print legally.");
	            break;
	        default:
	            System.out.println("✔ Standard print usage allowed.");
	    }
	    System.out.println("✔ Paper Type: " + paperType + ", Dimensions: " + dimenstions + "\n");
	}

	
	
}
