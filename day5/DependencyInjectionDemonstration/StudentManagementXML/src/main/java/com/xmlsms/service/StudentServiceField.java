package com.xmlsms.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmlsms.dao.StudentDAO;
import com.xmlsms.model.Student;

public class StudentServiceField {
	
	@Autowired
	 private StudentDAO studentDAO;

	 
		public void addStudent(Student student) {

	        studentDAO.save(student);

	    }
}
