package com.dayone.bookshelf;
import java.util.HashMap;
import java.util.LinkedList;
public class BookShelf {
	
	private  HashMap<String, LinkedList<BookNode>> bookshelf;
	
	
	// Initialization using constructor
	public BookShelf() {
	    bookshelf = new HashMap<>();
	}

	
	
	// Method to add a Book in bookshlef
	public void addBook(BookNode b) {
	    String genre = b.getGenere();

	    if (bookshelf.containsKey(genre)) {
	        bookshelf.get(genre).add(b);
	    } else {
	        LinkedList<BookNode> books = new LinkedList<>();
	        books.add(b);
	        bookshelf.put(genre, books);
	    }
	}


 
	// Method to borrow a book
	public void borrowBook(String title, String genre) {
	    LinkedList<BookNode> list = bookshelf.get(genre);
	    
	    if (list == null) {
	        System.out.println("Genre not found!");
	        return;
	    }
	    
	    for (int i = 0; i < list.size(); i++) {
	        BookNode b = list.get(i);
	        
	        if (b.getTitle().equalsIgnoreCase(title)) {
	            list.remove(i);
	            
	            if (list.isEmpty()) {
	                bookshelf.remove(genre);
	            }
	            
	            System.out.println(title + " borrowed successfully!");
	            return;  // Stop after removing
	        }
	    }
	    
	    System.out.println("Book not found in this genre.");
	}

		
		
	// Method to return a books
	public void returnBook(BookNode b) {
		
		
		if(bookshelf.containsKey(b.getGenere())) {
			bookshelf.get(b.getGenere()).add(b);
		}
		else {
			LinkedList<BookNode> list = new LinkedList<>();
			list.add(b);
			
			bookshelf.put(b.getGenere(), list);
		}
	
	
	
	}
		
	// Method to display books genre wise
	public void displayBooksByGenre(String genre) {
		
		if(bookshelf.get(genre) == null) {
			System.out.println("This Genre has no books");
			return;
		}
		
		else {
			System.out.println("Following books are available(" + genre + ")");
			for(BookNode book : bookshelf.get(genre)) {
				System.out.println("\n Title: " + book.getTitle());
				System.out.println(" Author: " + book.getAuthor());
				System.out.println("-------------------------------------");
			}
			
			
		}
		
		
		
		
		
	}
	
	
}
