package com.example.listingapp_rest.repository;

import com.example.listingapp_rest.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ListingRepository extends JpaRepository<Listing, Integer> {
    List<Listing> findAllByUserEmail(String email);
    List<Listing> findAllByCategoryId(int id);

}
