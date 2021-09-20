package com.example.listingapp_rest.service;

import com.example.listingapp_rest.model.User;
import com.example.listingapp_rest.repository.UserRepository;
import com.example.listingapp_rest.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceImpl {
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
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
