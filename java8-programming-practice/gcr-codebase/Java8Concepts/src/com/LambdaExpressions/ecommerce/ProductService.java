package com.LambdaExpressions.ecommerce;
import java.util.List;
import java.util.Comparator;

public class ProductService {

    // Method to sort products dynamically using a Comparator
    public void sortProducts(List<Product> products, Comparator<Product> comparator) {
        products.sort(comparator);
    }
}
