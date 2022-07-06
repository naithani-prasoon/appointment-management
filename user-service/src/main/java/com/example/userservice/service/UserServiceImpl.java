package com.example.userservice.service;

import com.example.userservice.repository.UserRepository;
import com.example.userservice.web.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User selectUser(String id) {
        //return  mongoTemplate.findById(id, User.class);
        return userRepository.findUserById(id);
                //.orElseThrow(NotFoundException::new);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        User updatedUser = userRepository.findUserById(id);
                //.orElseThrow(NotFoundException::new);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());

        return userRepository.save(updatedUser);
    }

    @Override
    public User deleteUser(String id) {
        User user = userRepository.findUserById(id);
        userRepository.deleteById(id);
        return user;
    }

    @Override
    public Iterable<User> listUser() {
        Iterable<User> users;
        users = userRepository.findAll();
        return users;
    }
}
