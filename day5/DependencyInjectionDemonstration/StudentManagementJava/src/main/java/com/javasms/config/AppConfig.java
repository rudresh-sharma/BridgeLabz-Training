package com.javasms.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import com.javasms.service.StudentService;
import com.javasms.service.StudentServiceConstructor;
import com.javasms.service.StudentServiceField;
import com.javasms.service.StudentServiceImpl;
import com.javasms.dao.StudentDAO;
import com.javasms.dao.StudentDAOImpl;
@Configuration
public class AppConfig {

    // ===========================
    // DAO Bean
    // ===========================
    @Bean
    public StudentDAO studentDAO() {
        return new StudentDAOImpl();
    }

    // ===========================
    // Setter Injection
    // ===========================
    @Bean
    public StudentService studentService() {

        StudentServiceImpl service = new StudentServiceImpl();

        service.setStudentDAO(studentDAO());

        return service;
    }

    // ===========================
    // Constructor Injection
    // ===========================
    @Bean
    public StudentServiceConstructor constructorStudentService() {

        return new StudentServiceConstructor(studentDAO());

    }

    // ===========================
    // Field Injection
    // ===========================
    @Bean
    public StudentServiceField studentServiceField() {

        return new StudentServiceField();

    }

}