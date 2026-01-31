package com.functionalinterfaces.implementinginterfaces.smartdevicecontrolinterface;

import java.util.Scanner;

public class MainApp {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to a Device Management System");
		
		int choice;
		
		do {
			System.out.println("Which Device you want to operate");
			System.out.println("1. AC ");
			System.out.println("2. Light");
			System.out.println("3. TV");
			System.out.println("0. Exit");
			
			System.out.println("Enter a choice");
			choice = sc.nextInt();
			int onOrOff;
			switch(choice) {
			case 1:
				showOnOff();
				onOrOff = sc.nextInt();
				
				if(onOrOff == 1) {
					new AC().turnOn();
				}
				else {
					new AC().turnOff();
				}
				break;
				
			case 2:
				showOnOff();
				onOrOff = sc.nextInt();
				
				if(onOrOff == 1) {
					new Lights().turnOn();
				}
				else {
					new Lights().turnOff();
				}
				break;
				
			case 3:
				showOnOff();
				onOrOff = sc.nextInt();
				
				if(onOrOff == 1) {
					new TV().turnOn();
				}
				else {
					new TV().turnOff();
				}
				break;
				
			default :
				System.out.println("Sorry Incorrect choice");
				break;
				
				
				
			}
			
		}while(choice != 0);
		
	}
	
	public static void showOnOff() {
		System.out.println("Select situation");
		System.out.println("1. On");
		System.out.println("2. Off");
	}
}
