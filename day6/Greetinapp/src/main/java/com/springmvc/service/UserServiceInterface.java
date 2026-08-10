package com.springmvc.service;



import com.springmvc.model.User;

import java.util.Optional;

public interface UserServiceInterface {

    boolean registerUser(User user);

    Optional<User> authenticate(String email, String password);

    Optional<User> getUserById(Long id);

    boolean updateUser(User user);

    boolean deleteUser(Long id);

    boolean userExists(String email);
}