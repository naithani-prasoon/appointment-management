package com.example.userservice.service;

import com.example.userservice.repository.UserRepository;
import com.example.userservice.web.exception.NotFoundException;
import com.example.userservice.web.mapper.UserMapper;
import com.example.userservice.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto selectUser(String id) {
        UserDto userDto = userMapper.userToUserDto(userRepository.findUserById(id));
        if (userDto == null) {
            throw new NotFoundException("User not found");
        }
        return userDto;
    }

    @Override
    public List<UserDto> getUserByFirstOrLastName(String name) {
        String [] res = name.split("\\s+");
        String firstName;
        String lastName;
        if (res.length == 2) {
            firstName = res[0];
            lastName = res[1];
            return userMapper
                    .userListToUserDtoList(userRepository.findUsersByFirstNameAndLastName(firstName, lastName));
        } else if (res.length == 1 && !res[0].isEmpty()) {
            List<UserDto> userDtoByFirstName = userMapper
                    .userListToUserDtoList(userRepository.findUsersByFirstName(res[0]));
            List<UserDto> userDtoByLastName = userMapper
                    .userListToUserDtoList(userRepository.findUsersByLastName(res[0]));

            if (userDtoByFirstName.size() != 0) return userDtoByFirstName;
            else return userDtoByLastName;
        } else {
            return userMapper.userListToUserDtoList(userRepository.findAll());
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        //save as user entity and return as userDto
        return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(userDto)));
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        userDto.setId(id);
        //save as user entity and return as userDto
        return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(userDto)));
    }

    @Override
    public UserDto deleteUser(String id) {
        UserDto userDto = userMapper.userToUserDto(userRepository.findUserById(id));
        userRepository.deleteById(id);
        return userDto;
    }

    @Override
    public List<UserDto> listUser() {
        return userMapper.userListToUserDtoList(userRepository.findAll());
    }
}
