package com.example.userservice.web.controller;

import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.example.userservice.web.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/api/v1/user")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") UUID userId){
        return new ResponseEntity<>(userService.selectUser(userId), HttpStatus.OK);
    }

    @GetMapping()
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<User> addNewUser(User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId, User user) {
        return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK) ;
    }

    @DeleteMapping("{userId}")
    public ResponseEntity deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
