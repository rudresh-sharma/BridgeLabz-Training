package com.xmlsms.dao;


import java.util.List;

import com.xmlsms.model.Student;


public interface StudentDAO {

    void save(Student student);

    Student findById(int studentId);

    List<Student> findAll();

    void update(Student student);

    void delete(int studentId);

}