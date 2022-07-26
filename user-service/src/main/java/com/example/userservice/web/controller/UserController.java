package com.example.userservice.web.controller;

import com.example.userservice.service.UserService;
import com.example.userservice.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path="/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.selectUser(userId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUserByFirstOrLastName(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(userService.getUserByFirstOrLastName(name), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDto> addNewUser(@Valid UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(userId, userDto), HttpStatus.OK) ;
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
