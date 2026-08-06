package com.completeannotationdisms.service;

import org.springframework.stereotype.Service;

import com.completeannotationdisms.model.Student;

@Service
public interface StudentService {

    void addStudent(Student student);

}