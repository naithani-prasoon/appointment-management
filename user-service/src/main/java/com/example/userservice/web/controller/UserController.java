package com.example.userservice.web.controller;

import com.example.userservice.service.UserService;
import com.example.userservice.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path="${service.api.path}")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userService.selectUser(userId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUserByFirstOrLastName(@RequestParam(required = false) String name) {
        if (name == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userService.getUserByFirstOrLastName(name), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDto> addNewUser(@Valid UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(userId, userDto), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId, @RequestParam(defaultValue = "false") boolean hardDelete) {
        userService.deleteUser(userId, hardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
