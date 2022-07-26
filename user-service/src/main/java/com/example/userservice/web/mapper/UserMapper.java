package com.example.userservice.web.mapper;


import com.example.userservice.web.domain.User;
import com.example.userservice.web.model.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public User userDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto userToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> userListToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(this::userToUserDto)
                .collect(Collectors.toList());
    }
}
