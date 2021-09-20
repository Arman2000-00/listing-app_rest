package com.example.listingapp_rest.endpoint;

import com.example.listingapp_rest.model.Category;
import com.example.listingapp_rest.model.User;
import com.example.listingapp_rest.repository.CategoryRepository;
import com.example.listingapp_rest.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CategoryEndpoint {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> findAllUsers() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") int id) {
        Optional<Category> byId = categoryService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.
                notFound().
                build();
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable("id") int id) {
        Optional<Category> byId = categoryService.findById(id);
        if (!byId.isPresent()) {
            return ResponseEntity.
                    notFound().
                    build();

        }
        categoryService.deleteById(id);
        return ResponseEntity.
                noContent()
                .build();
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("id") int id) {
        Optional<Category> byId = categoryService.findById(id);
        if (byId.isPresent()) {
            Category categoryById = byId.get();
            categoryById.setName(category.getName());
            return ResponseEntity.ok(categoryService.save(categoryById));
        }
        return ResponseEntity
                .notFound()
                .build();
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }
}
