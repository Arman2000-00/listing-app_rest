package com.example.listingapp_rest.service;

import com.example.listingapp_rest.model.Listing;
import com.example.listingapp_rest.repository.ListingRepository;
import com.example.listingapp_rest.service.impl.ListingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListingService implements ListingServiceImpl {

    private final ListingRepository listingRepository;


    @Override
    public Listing save(Listing listing) {
        return listingRepository.save(listing);
    }

    @Override
    public Optional<Listing> findById(int id) {
        return listingRepository.findById(id);
    }

    public Optional<Listing> findByEmail(String email) {
        return listingRepository.findByUserEmail(email);
    }


    @Override
    public List<Listing> findAll() {
        return listingRepository.findAll();
    }

    @Override
    public List<Listing> findAllListingsByUserEmail(String email) {
        return listingRepository.findAllByUserEmail(email);
    }

    @Override
    public List<Listing> findAllListingsByCategoryId(int id) {
        return listingRepository.findAllByCategoryId(id);
    }

    @Override
    public void deleteById(int id) {
    }
}
