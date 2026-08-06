package com.completeannotationdisms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.completeannotationdisms.model.Student;
import com.completeannotationdisms.dao.StudentDAO;

@Service
public class StudentServiceField implements StudentService {
	
	
	
	@Autowired
	private StudentDAO studentDAO;
	
	
	
	public StudentServiceField() {
		super();
		// TODO Auto-generated constructor stub
	}


@Override
		public void addStudent(Student student) {

	        studentDAO.save(student);

	    }
}
