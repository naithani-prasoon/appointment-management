package com.example.userservice.service;

import com.example.userservice.web.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User selectUser(UUID id);
    User createUser(User user);
    User updateUser(UUID userId, User user);
    void deleteUser(UUID id);
    Iterable<User> listUser();
}
