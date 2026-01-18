package com.generics.dynamiconlinemarketplace;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {

    private List<Product<?>> products = new ArrayList<>();

    public void addProduct(Product<?> product) {
        products.add(product);
    }

    public void displayProducts() {
        for (Product<?> product : products) {
            System.out.println(product);
        }
    }
}