package com.example.userservice.web.controller;

import com.example.userservice.service.UserService;
import com.example.userservice.web.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.selectUser(userId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUserByFirstOrLastName(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        return new ResponseEntity<>(userService.getUserByFirstOrLastName(firstName, lastName), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> addNewUser(@Valid User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @Valid User user) {
        return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK) ;
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
