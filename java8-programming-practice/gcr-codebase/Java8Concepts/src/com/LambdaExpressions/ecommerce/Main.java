package com.LambdaExpressions.ecommerce;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // Sample product list
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1200, 4.5, 10));
        products.add(new Product("Smartphone", 800, 4.7, 15));
        products.add(new Product("Headphones", 150, 4.3, 5));
        products.add(new Product("Smartwatch", 250, 4.6, 20));

        ProductService service = new ProductService();

        // Sort by Price (Low to High)
        System.out.println("Sorting by Price:");
        service.sortProducts(products, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        products.forEach(System.out::println);

        // Sort by Rating (High to Low)
        System.out.println("\nSorting by Rating:");
        service.sortProducts(products, (p1, p2) -> Double.compare(p2.getRating(), p1.getRating()));
        products.forEach(System.out::println);

        // Sort by Discount (High to Low)
        System.out.println("\nSorting by Discount:");
        service.sortProducts(products, (p1, p2) -> Double.compare(p2.getDiscount(), p1.getDiscount()));
        products.forEach(System.out::println);
    }
}
