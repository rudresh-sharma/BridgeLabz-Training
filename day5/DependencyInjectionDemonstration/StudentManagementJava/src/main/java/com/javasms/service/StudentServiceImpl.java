package com.javasms.service;

import org.springframework.beans.factory.annotation.Autowired;


import com.javasms.dao.StudentDAO;

public class StudentServiceImpl
        implements StudentService {

    private StudentDAO studentDAO;
    
   
    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
        
        System.out.println("Injecting Dependency Using Setting Injection");
    }

	@Override
	public void addStudent(com.javasms.model.Student student) {
		// TODO Auto-generated method stub
        studentDAO.save(student);

	}

}