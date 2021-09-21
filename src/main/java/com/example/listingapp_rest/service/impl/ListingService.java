package com.example.listingapp_rest.service.impl;

import com.example.listingapp_rest.model.Listing;

import java.util.List;
import java.util.Optional;

public interface ListingService {
    Listing save(Listing listing);

    Optional<Listing> findById(int id);

    List<Listing> findAll();

    List<Listing> findAllListingsByUserEmail(String email);

    List<Listing> findAllListingsByCategoryId(int id);

    void deleteById(int id);
}
