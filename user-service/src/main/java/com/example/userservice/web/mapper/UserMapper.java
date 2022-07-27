package com.example.userservice.web.mapper;

import com.example.userservice.domain.User;
import com.example.userservice.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}
