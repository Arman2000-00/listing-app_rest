package com.example.listingapp_rest.repository;

import com.example.listingapp_rest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
