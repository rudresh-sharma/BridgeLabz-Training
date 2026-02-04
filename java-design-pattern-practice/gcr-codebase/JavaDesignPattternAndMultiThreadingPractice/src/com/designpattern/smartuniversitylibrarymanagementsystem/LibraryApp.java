package com.designpattern.smartuniversitylibrarymanagementsystem;
public class LibraryApp {
    public static void main(String[] args) {

        LibraryCatalog catalog = LibraryCatalog.getInstance();

        User student = UserFactory.createUser("student", "Rudresh");
        User faculty = UserFactory.createUser("faculty", "Dr. Andrew");

        student.showRole();
        faculty.showRole();

        catalog.addObserver(student);
        catalog.addObserver(faculty);

        Book book = new Book.BookBuilder("Design Patterns Concepts")
                .author("Olivia Rodrigo")
                .edition("2nd")
                .genre("Software Engineering")
                .build();

        catalog.addBook(book);
    }
}