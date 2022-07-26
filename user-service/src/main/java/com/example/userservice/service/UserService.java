package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> listUser();
    UserDto selectUser(String id);
    List<UserDto> getUserByFirstOrLastName(String name);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(String id, UserDto userDto);
    void deleteUser(String id, boolean hardDelete);
    List<UserDto> filterList(List<User> userList);
}
