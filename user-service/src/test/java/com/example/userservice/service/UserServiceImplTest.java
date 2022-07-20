package com.example.userservice.service;

import com.example.userservice.repository.UserRepository;
import com.example.userservice.web.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id("1")
                .firstName("John")
                .lastName("Doe")
                .age(25)
                .emailAddress("JohnDoe@email.com")
                .phoneNumber("123456789")
                .build();
    }

    @Test
    void selectUser() {
        //given
        given(userRepository.findUserById(any())).willReturn(user);

        //when
        User foundUser = userService.selectUser("1");

        //then
        then(userRepository).should().findUserById(any());
        assertThat(foundUser).isNotNull();
    }

    @Test
    void createUser() {
        //given
        given(userRepository.save(any(User.class))).willReturn(user);

        //when
        User savedUser = userService.createUser(new User());

        //then
        then(userRepository).should().save(any(User.class));
        assertThat(savedUser).isNotNull();
    }

    @Test
    void updateUser() {
        //given
        given(userRepository.save(any(User.class))).willReturn(user);
        //when
        User savedUser = userService.updateUser("1", user);

        //then
        then(userRepository).should().save(any(User.class));
        assertThat(savedUser).isNotNull();
    }

    @Test
    void deleteUser() {
        //given
        //when
        userService.deleteUser("1");

        //then
        then(userRepository).should().deleteById(any());
    }

    @Test
    void listUser() {
        //given
        List<User> users = new ArrayList<User>();
        given(userRepository.findAll()).willReturn(users);

        //when
        userService.listUser();

        //then
        then(userRepository).should().findAll();
        assertThat(users).isNotNull();
    }

    @Test
    void getUserByFirstAndLastName() {
        //given
        List<User> users = new ArrayList<User>();
        given(userRepository.findUsersByFirstNameAndLastName(anyString(), anyString())).willReturn(users);

        //when
        userService.getUserByFirstOrLastName(user.getFirstName() + " " + user.getLastName());

        //then
        then(userRepository).should().findUsersByFirstNameAndLastName(anyString(), anyString());
        assertThat(users).isNotNull();

    }

    @Test
    void getUserByFirstName() {
        //given
        List<User> users = new ArrayList<User>();
        given(userRepository.findUsersByFirstName(anyString())).willReturn(users);

        //when
        userService.getUserByFirstOrLastName(user.getFirstName());

        //then
        then(userRepository).should().findUsersByFirstName(anyString());
        assertThat(users).isNotNull();
    }

    @Test
    void getUserByLastName() {
        //given
        List<User> users = new ArrayList<User>();
        given(userRepository.findUsersByLastName(anyString())).willReturn(users);

        //when
        userService.getUserByFirstOrLastName(user.getLastName());

        //then
        then(userRepository).should().findUsersByLastName(anyString());
        assertThat(users).isNotNull();
    }

    @Test
    void getUserByNoName() {
        //given
        List<User> users = new ArrayList<User>();
        given(userRepository.findAll()).willReturn(users);

        //when
        userService.getUserByFirstOrLastName("");

        //then
        then(userRepository).should().findAll();
        assertThat(users).isNotNull();
    }
}