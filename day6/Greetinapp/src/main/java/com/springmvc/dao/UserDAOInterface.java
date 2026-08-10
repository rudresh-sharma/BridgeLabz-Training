package com.springmvc.dao;

import com.springmvc.model.User;

import java.util.Optional;

public interface UserDAOInterface {

    void save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findById(Long id);

    boolean update(User user);

    boolean deleteById(Long id);
}