package com.example.userservice.service;

import com.example.userservice.web.model.UserDto;

import java.util.List;

public interface UserService {
    UserDto selectUser(String id);

    List<UserDto> getUserByFirstOrLastName(String name);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(String id, UserDto userDto);
    UserDto deleteUser(String id);
    List<UserDto> listUser();
}
