package com.example.listingapp_rest.service;

import com.example.listingapp_rest.model.Category;
import com.example.listingapp_rest.repository.CategoryRepository;
import com.example.listingapp_rest.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceImpl {
    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }


    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();

    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}
