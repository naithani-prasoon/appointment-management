package com.example.userservice.service;

import com.example.userservice.repository.UserRepository;
import com.example.userservice.web.model.NotFoundException;
import com.example.userservice.web.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User selectUser(UUID id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UUID userId, User user) {
        User updatedUser = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());

        return userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public Iterable<User> listUser() {
        Iterable<User> users;
        users = userRepository.findAll();
        return users;
    }
}
