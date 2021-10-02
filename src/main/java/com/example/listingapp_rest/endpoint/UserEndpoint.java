package com.example.listingapp_rest.endpoint;

import com.example.listingapp_rest.dto.UserAuthDto;
import com.example.listingapp_rest.dto.UserAuthResponseDto;
import com.example.listingapp_rest.dto.UserDto;
import com.example.listingapp_rest.dto.UserSaveDto;
import com.example.listingapp_rest.model.User;
import com.example.listingapp_rest.service.impl.UserService;
import com.example.listingapp_rest.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserEndpoint {
    private final UserService userService;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/users")
    public List<UserDto> findAllUsers() {
        List<User> allUsers = userService.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : allUsers) {
            UserDto userDto = mapper.map(user, UserDto.class);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("id") int id) {
        Optional<User> byId = userService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(mapper.map(byId.get(), UserDto.class));
        }
        return ResponseEntity.
                notFound().
                build();
    }

    @PostMapping("/users/auth")
    public ResponseEntity<?> auth(@RequestBody UserAuthDto userAuthDto) {
        Optional<User> byEmail = userService.findByEmail(userAuthDto.getEmail());
        if (!byEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = byEmail.get();
        if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
            return ResponseEntity.ok(UserAuthResponseDto.builder().
                    userDto(mapper.map(user, UserDto.class)).
                    token(jwtTokenUtil.generateToken(user.getEmail()))
                    .build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable("id") int id) {
        Optional<User> byId = userService.findById(id);
        if (!byId.isPresent()) {
            return ResponseEntity.
                    notFound().
                    build();

        }
        userService.deleteById(id);
        return ResponseEntity.
                noContent()
                .build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserSaveDto user, @PathVariable("id") int id) {
        Optional<User> byId = userService.findById(id);
        if (byId.isPresent()) {
            User userById = byId.get();
            userById.setName(user.getName());
            userById.setSurname(user.getSurname());
            userById.setEmail(user.getEmail());
            userById.setPassword(user.getPassword());
            userById.setUserRole(user.getUserRole());
            return ResponseEntity.ok().body(mapper.map(userService.save(userById), UserDto.class));

        }
        return ResponseEntity
                .notFound()
                .build();
    }

    @PostMapping("/users")
    public UserDto addUser(@RequestBody UserSaveDto user) {
        return mapper.map(userService.save(mapper.map(user, User.class)), UserDto.class);
    }
}
