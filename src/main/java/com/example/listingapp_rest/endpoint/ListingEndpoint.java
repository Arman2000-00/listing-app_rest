package com.example.listingapp_rest.endpoint;

import com.example.listingapp_rest.model.Category;
import com.example.listingapp_rest.model.Listing;
import com.example.listingapp_rest.model.User;
import com.example.listingapp_rest.service.CategoryServiceImpl;
import com.example.listingapp_rest.service.ListingServiceImpl;
import com.example.listingapp_rest.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ListingEndpoint {
    private final ListingServiceImpl listingServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final CategoryServiceImpl categoryServiceImpl;


    @GetMapping("/listings")
    public List<Listing> findAllListing() {
        return listingServiceImpl.findAll();
    }

    @GetMapping("/listings/{id}")
    public ResponseEntity<Listing> findListingById(@PathVariable("id") int id) {
        Optional<Listing> byId = listingServiceImpl.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.
                notFound().
                build();
    }

    @GetMapping("/listings/byCategory/{categoryId}")
    public ResponseEntity<List<Listing>> findAllListingsByCategoryId(@PathVariable("categoryId") int categoryId) {
        Optional<Category> byId = categoryServiceImpl.findById(categoryId);
        if (byId.isPresent()) {
            return ResponseEntity.ok(listingServiceImpl.findAllListingsByCategoryId(categoryId));
        }
        return ResponseEntity.
                notFound().
                build();
    }

    @GetMapping(" /listings/byUser/{email}")
    public ResponseEntity<List<Listing>> findAllListingsByUserEmail(@PathVariable("email") String email) {
        Optional<User> byEmail = userServiceImpl.findByEmail(email);
        if (byEmail.isPresent()) {
            return ResponseEntity.ok(listingServiceImpl.findAllListingsByUserEmail(email));
        }
        return ResponseEntity.
                notFound().
                build();
    }

    @DeleteMapping("/listings/{id}")
    public ResponseEntity<Listing> deleteListingById(@PathVariable("id") int id) {
        Optional<Listing> byId = listingServiceImpl.findById(id);
        if (!byId.isPresent()) {
            return ResponseEntity.
                    notFound().
                    build();

        }
        listingServiceImpl.deleteById(id);
        return ResponseEntity.
                noContent()
                .build();
    }

    @PutMapping("/listings/{id}")
    public ResponseEntity<Listing> updateListing(@RequestBody Listing listing, @PathVariable("id") int id) {
        Optional<Listing> byId = listingServiceImpl.findById(id);
        if (byId.isPresent()) {
            Listing listingById = byId.get();
            listing.setTitle(listingById.getTitle());
            listing.setDescription(listingById.getDescription());
            listing.setPrice(listingById.getPrice());
            listing.setUser(listingById.getUser());
            listing.setCategory(listingById.getCategory());
            return ResponseEntity.ok(listingServiceImpl.save(listingById));

        }
        return ResponseEntity
                .notFound()
                .build();
    }

    @PostMapping("/listings")
    public Listing addListing(@RequestBody Listing listing) {
        return listingServiceImpl.save(listing);
    }
}
