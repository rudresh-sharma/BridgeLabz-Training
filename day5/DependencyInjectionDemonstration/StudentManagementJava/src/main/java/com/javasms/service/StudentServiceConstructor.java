package com.javasms.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.javasms.dao.StudentDAO;
import com.javasms.model.Student;

public class StudentServiceConstructor {
	 private StudentDAO studentDAO;

	 
	 

	   @Autowired
	    public StudentServiceConstructor(StudentDAO studentDAO) {
		super();
		this.studentDAO = studentDAO;
		System.out.println("Injecting Dependency using Constructor");
	}





		public void addStudent(Student student) {

	        studentDAO.save(student);

	    }
}
