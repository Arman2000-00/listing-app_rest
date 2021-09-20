package com.example.listingapp_rest.endpoint;

import com.example.listingapp_rest.model.Listing;
import com.example.listingapp_rest.model.User;
import com.example.listingapp_rest.service.ListingService;
import com.example.listingapp_rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ListingEndpoint {
    private final ListingService listingService;



    @GetMapping("/listings")
    public List<Listing> findAllListing() {
        return listingService.findAll();
    }

    @GetMapping("/listings/{id}")
    public ResponseEntity<Listing> findListingById(@PathVariable("id") int id) {
        Optional<Listing> byId = listingService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.
                notFound().
                build();
    }

    @GetMapping("/listings/byCategory/{categoryId}")
    public ResponseEntity<Listing> findAllListingsByCategoryId(@PathVariable("categoryId") int categoryId) {
        Optional<Listing> byId = listingService.findById(categoryId);
        if (byId.isPresent()) {
            listingService.findAllListingsByCategoryId(categoryId);
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.
                notFound().
                build();
    }

    @GetMapping(" /listings/byUser/{email}")
    public ResponseEntity<Listing> findAllListingsByUserEmail(@PathVariable("email") String email) {
        Optional<Listing> byEmail = listingService.findByEmail(email);
        if (byEmail.isPresent()) {
            listingService.findAllListingsByUserEmail(email);
            return ResponseEntity.ok(byEmail.get());
        }
        return ResponseEntity.
                notFound().
                build();
    }

    @DeleteMapping("/listings/{id}")
    public ResponseEntity<User> deleteListingById(@PathVariable("id") int id) {
        Optional<Listing> byId = listingService.findById(id);
        if (!byId.isPresent()) {
            return ResponseEntity.
                    notFound().
                    build();

        }
        listingService.deleteById(id);
        return ResponseEntity.
                noContent()
                .build();
    }

    @PutMapping("/listings/{id}")
    public ResponseEntity<Listing> updateListing(@RequestBody Listing listing, @PathVariable("id") int id) {
        Optional<Listing> byId = listingService.findById(id);
        if (byId.isPresent()) {
            Listing listingById = byId.get();
            listing.setTitle(listingById.getTitle());
            listing.setDescription(listingById.getDescription());
            listing.setPrice(listingById.getPrice());
            listing.setUser(listingById.getUser());
            listing.setCategory(listingById.getCategory());
            return ResponseEntity.ok(listingService.save(listingById));

        }
        return ResponseEntity
                .notFound()
                .build();
    }

    @PostMapping("/listings")
    public Listing addListing(@RequestBody Listing listing) {
        return listingService.save(listing);
    }
}
