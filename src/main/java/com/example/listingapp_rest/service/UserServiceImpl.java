package com.example.listingapp_rest.service;

import com.example.listingapp_rest.model.User;
import com.example.listingapp_rest.repository.UserRepository;
import com.example.listingapp_rest.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
