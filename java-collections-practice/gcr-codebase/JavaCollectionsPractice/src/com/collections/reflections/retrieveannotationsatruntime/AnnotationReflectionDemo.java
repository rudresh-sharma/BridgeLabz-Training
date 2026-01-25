package com.collections.reflections.retrieveannotationsatruntime;
public class AnnotationReflectionDemo {
    public static void main(String[] args) {

        Class<Book> clazz = Book.class;

        if (clazz.isAnnotationPresent(Author.class)) {
            Author author = clazz.getAnnotation(Author.class);
            System.out.println("Author Name: " + author.name());
        }
    }
}
