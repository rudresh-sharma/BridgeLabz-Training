package com.daynine.binarysearchtree.ecommerceproductinventory;

public class Product {

    private String name;
    private String sku;      // Key for BST
    private double price;

    private Product left;
    private Product right;

    public Product(String name, String sku, double price) {
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.left = null;
        this.right = null;
    }

    // Copy constructor
    public Product(Product p) {
        this.name = p.name;
        this.sku = p.sku;
        this.price = p.price;
        this.left = null;
        this.right = null;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public double getPrice() {
        return price;
    }

    public Product getLeft() {
        return left;
    }

    public Product getRight() {
        return right;
    }

    public void setLeft(Product left) {
        this.left = left;
    }

    public void setRight(Product right) {
        this.right = right;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
