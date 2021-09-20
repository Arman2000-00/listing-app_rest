package com.example.listingapp_rest.service.impl;

import com.example.listingapp_rest.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceImpl {

    Optional<Category> findById(int id);

    Category save(Category category);

    List<Category> findAll();

    void deleteById(int id);
}

