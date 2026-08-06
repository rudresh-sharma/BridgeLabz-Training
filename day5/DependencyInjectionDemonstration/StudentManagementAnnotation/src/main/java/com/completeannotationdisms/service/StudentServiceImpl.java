package com.completeannotationdisms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.completeannotationdisms.model.Student;



import com.completeannotationdisms.dao.StudentDAO;

@Service
public class StudentServiceImpl
        implements StudentService {

    private StudentDAO studentDAO;
    
   @Autowired
    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
        
        System.out.println("Injecting Dependency Using Setting Injection");
    }

	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
        studentDAO.save(student);

	}

}