package com.completeannotationdisms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.completeannotationdisms.model.Student;



import com.completeannotationdisms.dao.StudentDAO;

@Service
public class StudentServiceConstructor implements StudentService{
	 private StudentDAO studentDAO;

	 
	 

	   @Autowired
	    public StudentServiceConstructor(StudentDAO studentDAO) {
		super();
		this.studentDAO = studentDAO;
		System.out.println("Injecting Dependency using Constructor");
	}




	   @Override
		public void addStudent(Student student) {

	        studentDAO.save(student);

	    }
}
