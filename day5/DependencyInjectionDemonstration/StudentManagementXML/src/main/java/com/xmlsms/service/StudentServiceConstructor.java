package com.xmlsms.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmlsms.dao.StudentDAO;
import com.xmlsms.model.Student;

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
