package com.constructors.levelone;

public class BookMain  {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Creating book object
		Book book1 = new Book();		
		Book book2 = new Book("Freedom to Live", "Albert Johnson", 1000.0f);
		Book book3 = new Book();
		
		book3.setData("The Art of Living", "Dr Bridgestone", 0.20f);
		
		// Getting data
		String book1Data = book1.getData();
		String book2Data = book2.getData();
		String book3Data = book3.getData();
		
		
		// Printing data
		System.out.println(book1Data);
		System.out.println(book2Data);
		System.out.println(book3Data);

}
}
