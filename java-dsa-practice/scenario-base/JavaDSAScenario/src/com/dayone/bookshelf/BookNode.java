package com.dayone.bookshelf;

public class BookNode {

	private String title;
	private String author;
	private String genere;
	
	
	BookNode(String title, String author, String genere){
		this.title = title;
		this.author = author;
		this.genere = genere;
	}
	
	
	
	
	
	// Getter and Setters
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	
	
	
	
	
	
	
	
	
}
