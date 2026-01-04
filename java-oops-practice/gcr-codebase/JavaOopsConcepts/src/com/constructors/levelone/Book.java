package com.constructors.levelone;

public class Book {

		private String title;
		private String author;
		private float price;
		
		private String[] books = {"Atomic Life","Freedom to Live","The Art of Living"};
		private boolean[] availibility= {true, false,true};
		
		public boolean isAvailable(String input) {
			for ( int i =0; i < books.length; i++) {
				if( books[i].equals(input))
				  return availibility[i];
			}
			return false;
		}
		
		public Book() {
			this.title = "Atomic Life";
			this.author = "James Clark";
			this.price = 90.23f;
		}
		
		
		public Book(String title, String author, float price) {
			this.title = title;
			this.author = author;
			this.price = price;
		}
		
		
		public void setData(String title, String author, float price) {
			this.title = title;
			this.author = author;
			this.price = price;
		}
		
		public String getData() {
			return title + " " + author + "  " + price;
		}
		
}