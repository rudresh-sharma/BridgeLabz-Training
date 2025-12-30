package com.objectmodeling;

public class EcommerceMain {

	    public static void main(String[] args) {

	        // Create Customer
	        CustomerEcommerce customer = new CustomerEcommerce("Amit");

	        // Create Products
	        Product p1 = new Product("Laptop", 60000);
	        Product p2 = new Product("Mouse", 800);

	        // Create Order
	        Order order = new Order(101);

	        // Customer places order (Communication)
	        customer.placeOrder(order, p1);
	        customer.placeOrder(order, p2);

	        // Show order details
	        order.showOrder();
	    }

}
