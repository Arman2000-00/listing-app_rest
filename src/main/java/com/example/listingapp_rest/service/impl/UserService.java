package com.example.listingapp_rest.service.impl;

import com.example.listingapp_rest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(int id);

    User save(User user);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    void deleteById(int id);
}
