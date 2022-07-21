package com.example.userservice.service;

import com.example.userservice.repository.UserRepository;
import com.example.userservice.web.model.NotFoundException;
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
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<User> getUserByFirstOrLastName(String name) {
        String [] res = name.split("\\s+");
        String firstName;
        String lastName;
        if (res.length == 2) {
            firstName = res[0];
            lastName = res[1];
            return userRepository.findUsersByFirstNameAndLastName(firstName, lastName);
        } else if (res.length == 1 && !res[0].isEmpty()) {
            List<User> userByFirstName = userRepository.findUsersByFirstName(res[0]);
            List<User> userByLastName = userRepository.findUsersByLastName(res[0]);
            if (userByFirstName.size() != 0) return userByFirstName;
            else return userByLastName;
        } else {
            return userRepository.findAll();
        }
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        user.setId(id);
        return userRepository.save(user);
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
