package com.example.userservice.service;

import com.example.userservice.repository.UserRepository;
import com.example.userservice.web.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    //TODO add exception handler
    private final UserRepository userRepository;

    @Override
    public User selectUser(String id) {
        return userRepository.findUserById(id);
                //.orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public List<User> getUserByFirstOrLastName(String firstName, String lastName) {
        return userRepository.findUsersByFirstNameOrLastName(firstName, lastName);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        User updatedUser = userRepository.findUserById(id);
                //.orElseThrow(NotFoundException::new);
        updatedUser = user;

        return userRepository.save(updatedUser);
    }

    @Override
    public User deleteUser(String id) {
        User user = userRepository.findUserById(id);
        userRepository.deleteById(id);
        return user;
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }
}
