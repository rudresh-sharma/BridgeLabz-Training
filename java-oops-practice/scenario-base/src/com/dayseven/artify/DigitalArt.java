package com.dayseven.artify;

public class DigitalArt extends Artwork{
	
	private String fileFormat;
	private double fileSize;
	
	
	
	
	
	
	public DigitalArt(String id,String title, String artist, double price, String licenseType, String fileFormat,double fileSize) {
		super(id, title, artist, price, licenseType);
		this.fileFormat = fileFormat;
		this.fileSize = fileSize;
	}
	// getter and setters
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	public double getFileSize() {
		return fileSize;
	}
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
	
	

	// Method to print Artwork details
	@Override
	public void printArtDetails() {
		super.printArtDetails();
		
		System.out.println("FileFormat: " + this.getFileFormat());
		System.out.println("FileSize: " + this.getFileSize());
		
		
	}
	
	
	@Override
	public void license(User user) {
	    System.out.println("\n--- Digital Art Licensing ---");
	    System.out.println("Artwork: " + getTitle());
	    System.out.println("Artist: " + getArtist());
	    System.out.println("License Type: " + getLicenseType());

	    switch (getLicenseType()) {
	        case "Personal":
	            System.out.println("✔ Use for personal purposes only (wallpapers, social media).");
	            break;
	        case "Commercial":
	            System.out.println("✔ Use in advertisements, websites, and commercial projects.");
	            break;
	        case "Exclusive":
	            System.out.println("✔ Exclusive ownership. Only you can use this artwork.");
	            break;
	        case "NFT":
	            System.out.println("✔ Ownership recorded on blockchain.");
	            break;
	        default:
	            System.out.println("✔ Standard digital usage allowed.");
	    }
	    System.out.println("✔ You can download the file: " + fileFormat + " (" + fileSize + "MB)\n");
	}

	
	
	
}	
