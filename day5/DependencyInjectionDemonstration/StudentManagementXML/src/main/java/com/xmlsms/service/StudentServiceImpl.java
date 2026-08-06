package com.xmlsms.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmlsms.dao.StudentDAO;
import com.xmlsms.model.Student;

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

        studentDAO.save(student);

    }

}