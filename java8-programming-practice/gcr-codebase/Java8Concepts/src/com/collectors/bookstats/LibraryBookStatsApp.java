package com.collectors.bookstats;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

public class LibraryBookStatsApp {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();

        books.add(new Book("The Alchemist", "Fiction", 208));
        books.add(new Book("Sapiens", "Non-Fiction", 443));
        books.add(new Book("The Da Vinci Code", "Mystery", 689));
        books.add(new Book("Dune", "Sci-Fi", 412));
        books.add(new Book("Becoming", "Biography", 448));
        books.add(new Book("1984", "Fiction", 328));
        books.add(new Book("Educated", "Biography", 334));
        books.add(new Book("Project Hail Mary", "Sci-Fi", 496));
        books.add(new Book("Atomic Habits", "Non-Fiction", 320));
        books.add(new Book("The Silent Patient", "Mystery", 325));

        System.out.println("Library Book Statistics by Genre");
        System.out.println("--------------------------------");

        Map<String, IntSummaryStatistics> statsByGenre = books.stream()
                .collect(Collectors.groupingBy(
                        Book::getGenre,
                        Collectors.summarizingInt(Book::getPages)
                ));

        statsByGenre.forEach((genre, stats) -> {
            System.out.println("Genre: " + genre);
            System.out.println("  Total pages   : " + stats.getSum());
            System.out.println("  Average pages : " + String.format("%.1f", stats.getAverage()));
            System.out.println("  Max pages     : " + stats.getMax());
            System.out.println("  Book count    : " + stats.getCount());
            System.out.println("--------------------------------");
        });

        System.out.println("Total books analyzed: " + books.size());
    }
}