package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.model.UserDto;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.web.exception.NotFoundException;
import com.example.userservice.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<UserDto> listUser() {
        List<User> userList = userRepository.findAll();
        return filterList(userList);
    }

    @Override
    public UserDto selectUser(String id) {
        User user = userRepository.findUserById(id);
        if (user == null || user.getIsDeleted()) {
            throw new NotFoundException("User not found");
        }
        return mapper.userToUserDto(user);
    }

    @Override
    public List<UserDto> getUserByFirstOrLastName(String name) {
        String [] res = name.split("\\s+");
        String firstName;
        String lastName;
        if (res.length == 2) {
            firstName = res[0];
            lastName = res[1];
            return filterList(userRepository.findUsersByFirstNameAndLastName(firstName, lastName));
        } else if (res.length == 1 && !res[0].isEmpty()) {
            List<UserDto> userDtoByFirstName = filterList(userRepository.findUsersByFirstName(res[0]));
            List<UserDto> userDtoByLastName = filterList(userRepository.findUsersByLastName(res[0]));

            if (!userDtoByFirstName.isEmpty()) return userDtoByFirstName;
            else return userDtoByLastName;
        } else {
            return listUser();
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        //save as user entity and return as userDto
        return mapper.userToUserDto(userRepository.save(mapper.userDtoToUser(userDto)));
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        userDto.setId(id);
        //save as user entity and return as userDto
        return mapper.userToUserDto(userRepository.save(mapper.userDtoToUser(userDto)));
    }

    @Override
    public void deleteUser(String id, boolean hardDelete) {
        if (hardDelete) {
            userRepository.deleteById(id);
        } else {
            User user = userRepository.findUserById(id);
            user.setIsDeleted(true);
            userRepository.save(user);
        }

        String url = "http://localhost:8081/api/v1/appointments/delete-user/" + id;
        restTemplate.delete(url);
    }

    @Override
    public List<UserDto> filterList(List<User> userList) {
        List<UserDto> filterList = new ArrayList<>();
        for (User user: userList) {
            Boolean isDeleted = user.getIsDeleted();
            if (Boolean.FALSE.equals(isDeleted)) {
                filterList.add(mapper.userToUserDto(user));
            }
        }
        return filterList;
    }
}
