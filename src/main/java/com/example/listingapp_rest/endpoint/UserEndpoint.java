package com.example.listingapp_rest.endpoint;

import com.example.listingapp_rest.model.User;
import com.example.listingapp_rest.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserEndpoint {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userServiceImpl.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") int id) {
        Optional<User> byId = userServiceImpl.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.
                notFound().
                build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable("id") int id) {
        Optional<User> byId = userServiceImpl.findById(id);
        if (!byId.isPresent()) {
            return ResponseEntity.
                    notFound().
                    build();

        }
        userServiceImpl.deleteById(id);
        return ResponseEntity.
                noContent()
                .build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") int id) {
        Optional<User> byId = userServiceImpl.findById(id);
        if (byId.isPresent()) {
            User userById = byId.get();
            userById.setName(user.getName());
            userById.setSurname(user.getSurname());
            userById.setEmail(user.getEmail());
            userById.setPassword(user.getPassword());
            userById.setUserRole(user.getUserRole());
            return ResponseEntity.ok(userServiceImpl.save(userById));

        }
       return  ResponseEntity
               .notFound()
               .build();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userServiceImpl.save(user);
    }
}
