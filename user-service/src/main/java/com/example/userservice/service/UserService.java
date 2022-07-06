package com.example.userservice.service;

import com.example.userservice.web.model.User;

public interface UserService {
    User selectUser(String id);
    User createUser(User user);
    User updateUser(String userId, User user);
    User deleteUser(String id);
    Iterable<User> listUser();
}
