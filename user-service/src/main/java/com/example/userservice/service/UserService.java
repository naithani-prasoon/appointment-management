package com.example.userservice.service;

import com.example.userservice.web.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User selectUser(String id);

    List<User> getUserByFirstOrLastName(String firstName, String lastName);
    User createUser(User user);
    User updateUser(String id, User user);
    User deleteUser(String id);
    List<User> listUser();
}
