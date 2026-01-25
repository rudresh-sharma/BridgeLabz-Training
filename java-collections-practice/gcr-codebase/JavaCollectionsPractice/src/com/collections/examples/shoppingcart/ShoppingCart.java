package com.collections.examples.shoppingcart;

import java.util.*;

public class ShoppingCart {

	// HashMap: Fast lookup of product -> price
	private final Map<String, Double> productPrices = new HashMap<>();

	// LinkedHashMap: Maintains insertion order of products
	private final Map<String, Double> orderedCart = new LinkedHashMap<>();

	// TreeMap: Sorted by price (ascending)
	private final TreeMap<Double, List<String>> sortedByPrice = new TreeMap<>();

	// Add product to cart with price
	public void addItem(String product, double price) {
		// Store price in HashMap (fast lookup)
		productPrices.put(product, price);

		// Add to ordered cart (preserves addition order)
		orderedCart.put(product, price);

		// Add to TreeMap sorted by price (handles same price with list)
		sortedByPrice.computeIfAbsent(price, _ -> new ArrayList<>()).add(product);
	}

	// Calculate total cart value 
	public double getTotal() {
		double total = 0;
		for (double price : orderedCart.values()) {
			total += price;
		}
		return total;
	}

	// Display cart in different ways
	public void displayCart() {
		System.out.println("\n=== Shopping Cart Summary ===\n");

		// 1. HashMap - No particular order, fastest access
		System.out.println("HashMap (unordered, fast lookup):");
		productPrices.forEach((item, price) -> System.out.printf("  %-15s : $%.2f%n", item, price));

		System.out.println();

		// 2. LinkedHashMap - Order in which items were added
		System.out.println("LinkedHashMap (order of addition):");
		orderedCart.forEach((item, price) -> System.out.printf("  %-15s : $%.2f%n", item, price));

		System.out.println();

		// 3. TreeMap - Sorted by price (lowest to highest)
		System.out.println("TreeMap (sorted by price):");
		for (Map.Entry<Double, List<String>> entry : sortedByPrice.entrySet()) {
			double price = entry.getKey();
			for (String item : entry.getValue()) {
				System.out.printf("  %-15s : $%.2f%n", item, price);
			}
		}

		System.out.println("-------------------------------");
		System.out.printf("Total Amount     : $%.2f%n", getTotal());
		System.out.println("================================");
	}

	// Simple demo
	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();

		System.out.println("Adding items to cart...");

		cart.addItem("Notebook", 45.50);
		cart.addItem("Pen", 12.00);
		cart.addItem("Water Bottle", 250.00);
		cart.addItem("Eraser", 8.00);
		cart.addItem("Highlighter", 25.00);
		cart.addItem("Notebook", 45.50); // same item again (price same)

		cart.displayCart();

		System.out.println("\nPrice of Notebook: $" + cart.productPrices.get("Notebook"));
	}
}