package com.mycontactapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mycontactapp.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    boolean existsByEmail(String email);  // no @Query — let Spring Data derive it
    
    List<Contact> findByIsFavouriteTrue();
    List<Contact> findByFirstNameStartingWith(String name);
    
}