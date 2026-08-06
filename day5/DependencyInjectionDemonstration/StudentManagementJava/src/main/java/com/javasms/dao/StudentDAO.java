package com.javasms.dao;


import java.util.List;


import com.javasms.model.Student;

public interface StudentDAO {

    void save(Student student);

    Student findById(int studentId);

    List<Student> findAll();

    void update(Student student);

    void delete(int studentId);

}