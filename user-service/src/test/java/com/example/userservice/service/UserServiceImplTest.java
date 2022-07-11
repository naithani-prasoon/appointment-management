package com.example.userservice.service;

import com.example.userservice.repository.UserRepository;
import com.example.userservice.web.model.User;
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

    @Test
    void selectUser() {
        //given
        User user = new User();
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
        User user = new User();
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
        User user = new User();
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
        User user = new User();
        List<User> users = new ArrayList<User>();
        given(userRepository.findAll()).willReturn(users);

        //when
        userService.listUser();

        //then
        then(userRepository).should().findAll();
    }
}