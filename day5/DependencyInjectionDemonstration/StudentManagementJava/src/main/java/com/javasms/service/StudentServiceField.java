package com.javasms.service;

import org.springframework.beans.factory.annotation.Autowired;




import com.javasms.dao.StudentDAO;
import com.javasms.model.Student;
public class StudentServiceField {
	
	
	
	@Autowired
	private StudentDAO studentDAO;
	
	
	
	public StudentServiceField() {
		super();
		// TODO Auto-generated constructor stub
	}



		public void addStudent(Student student) {

	        studentDAO.save(student);

	    }
}
