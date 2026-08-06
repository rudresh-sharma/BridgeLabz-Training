package com.xmlsms;



import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmlsms.model.Student;
import  com.xmlsms.service.StudentService;
import com.xmlsms.service.StudentServiceConstructor;
import com.xmlsms.service.StudentServiceField;

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("Enter Choice using which want to add");
    	System.out.println("1. Setter Injection");
    	System.out.println("2. Constructior Injection ");
    	System.out.println("3. Field Injection ");
    	System.out.println("4. Stop");
	}
    public static void main(String[] args) {


        // 1. Create Spring Container
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");


        // 2. Get object from Spring Container
        StudentService studentService =
                context.getBean(
                        "studentService",
                        StudentService.class
                );
        
        
        StudentServiceConstructor constStudService = 
        		context.getBean("constStudeService", StudentServiceConstructor.class);

        // 3. Create Student object
        
        StudentServiceField fieldStudService = 
        		context.getBean("studentServiceField",StudentServiceField.class);
        
        while(true) {
        	
        	showMenu();
        	int choice = scanner.nextInt();
        	scanner.nextLine();
        	System.out.println("\n\nEnter Details of Student : ");
        	System.out.print("First Name :  ");
        	String firstName = scanner.nextLine();
        	System.out.print("Last Name: ");
        	String lastName = scanner.nextLine();
        	System.out.print("Enter Email: ");
        	String email = scanner.nextLine();
        	System.out.print("Enter Course: ");
        	String course = scanner.nextLine();
        	
        	Student student = new Student( firstName, lastName, email, course);
        	
        	switch(choice) {
        		case 1 -> {
        			studentService.addStudent(student);
        			break;
        		}
        		case 2 -> {
        			constStudService.addStudent(student);
        			break;
        		}
        		case 3 -> {
        			System.out.println("Using Field Injection");
        			fieldStudService.addStudent(student);
        		}
        		case 4 -> {
        			return ;
        		}
        	}
        	
        }


   
        
        
    }
}