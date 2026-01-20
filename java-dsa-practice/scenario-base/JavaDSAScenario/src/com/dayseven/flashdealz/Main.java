package com.dayseven.flashdealz;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Product> productList = new ArrayList<>();
		
		productList.add(new Product("Laptop", 35.5));
		productList.add(new Product("Shoes", 60.0));
		productList.add(new Product("Phone", 45.0));
		productList.add(new Product("Headphones", 20.0));
		productList.add(new Product("Smart Watch", 50.0));
		productList.add(new Product("Gaming Mouse", 30.0));
		productList.add(new Product("Bluetooth Speaker", 40.0));
		productList.add(new Product("LED TV", 55.0));
		productList.add(new Product("Refrigerator", 25.0));
		productList.add(new Product("Microwave Oven", 35.0));
		productList.add(new Product("Washing Machine", 48.0));
		productList.add(new Product("Air Conditioner", 52.0));
		productList.add(new Product("Keyboard", 15.0));
		productList.add(new Product("Fitness Band", 38.0));
		productList.add(new Product("Camera", 42.0));
		productList.add(new Product("Tablet", 33.0));
		productList.add(new Product("Power Bank", 22.0));
		productList.add(new Product("Printer", 28.0));
		productList.add(new Product("Monitor", 36.0));
		productList.add(new Product("Desk Lamp", 12.0));
		productList.add(new Product("Office Chair", 44.0));
		productList.add(new Product("External Hard Drive", 31.0));
		productList.add(new Product("Router", 18.0));
		productList.add(new Product("Mechanical Keyboard", 46.0));
		productList.add(new Product("Graphic Card", 58.0));
		productList.add(new Product("SSD Drive", 39.0));
		productList.add(new Product("VR Headset", 53.0));
		productList.add(new Product("Drone", 47.0));
		productList.add(new Product("Tripod", 17.0));
		productList.add(new Product("Microphone", 27.0));
		
		int n = productList.size();
		
		QuickSort.quickSort(productList, 0 , n-1);
		
		System.out.printf("%-24s%-10s\n","Product","Discount");
        System.out.print("_________________________________\n");

		for(Product a: productList) {
			System.out.printf("%-24 s%-10.1f",a.getName(),a.getDiscount());
			System.out.println();
		}

	}

}