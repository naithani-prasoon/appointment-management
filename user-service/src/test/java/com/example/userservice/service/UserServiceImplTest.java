package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.model.UserDto;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.web.exception.NotFoundException;
import com.example.userservice.web.mapper.UserMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@WireMockTest
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper mapper;

    @InjectMocks
    UserServiceImpl userService;

    @Value("${appointment.url}")
    private String baseUrl;


    WireMockServer wm = new WireMockServer(options().dynamicPort());
    UserDto userDto;
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
                .isDeleted(false)
                .build();
        userDto = UserDto.builder()
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
        given(userRepository.findUserById("1")).willReturn(user);
        given(mapper.userToUserDto(user)).willReturn(userDto);

        //when
        UserDto foundUserDto = userService.selectUser("1");

        //then
        then(userRepository).should().findUserById(any(String.class));
        assertThat(foundUserDto).isNotNull();
    }

    @Test
    void selectUserThrowNotFoundException() {
        //given
        given(userRepository.findUserById("2")).willThrow(new NotFoundException("user not found"));

        //when

        //then
        assertThatThrownBy(() -> userService.selectUser("2"))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void createUser() {
        //given
        given(userRepository.save(any(User.class))).willReturn(user);
        given(mapper.userToUserDto(user)).willReturn(userDto);
        given(mapper.userDtoToUser(userDto)).willReturn(user);
        //when
        UserDto savedUser = userService.createUser(userDto);

        //then
        then(userRepository).should().save(any(User.class));
        assertThat(savedUser).isNotNull();
    }

    @Test
    void updateUser() {
        //given
        given(userRepository.save(any(User.class))).willReturn(user);
        given(mapper.userToUserDto(user)).willReturn(userDto);
        given(mapper.userDtoToUser(userDto)).willReturn(user);
        //when
        UserDto savedUserDto = userService.updateUser("1", userDto);

        //then
        then(userRepository).should().save(any(User.class));
        assertThat(savedUserDto).isNotNull();
    }

    @Test
    void hardDeleteUser() {
        //given
        willDoNothing().given(userRepository).deleteById("1");
        stubFor(delete(urlEqualTo("/api/v1/appointments/delete-user/" + "1"))
                .willReturn(noContent()));

        //when
        userService.deleteUser("1", true);

        //then
        then(userRepository).should().deleteById(any());
    }

    @Test
    void softDeleteUser() {
        //given
        given(userRepository.findUserById("1")).willReturn(user);
        given(userRepository.save(user)).willReturn(user);

        //when
        userService.deleteUser("1", false);

        //then
        then(userRepository).should().save(any(User.class));
        assertThat(user).isNotNull();
        assertThat(user.getIsDeleted()).isTrue();
    }

    @Test
    void listUser() {
        //given
        List<User> users = new ArrayList<>();
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
        List<User> users = new ArrayList<>();
        given(userRepository.findUsersByFirstNameAndLastName(anyString(), anyString())).willReturn(users);

        //when
        userService.getUserByFirstOrLastName(userDto.getFirstName() + " " + userDto.getLastName());

        //then
        then(userRepository).should().findUsersByFirstNameAndLastName(anyString(), anyString());
        assertThat(users).isNotNull();

    }

    @Test
    void getUserByFirstName() {
        //given
        List<User> users = new ArrayList<>();
        given(userRepository.findUsersByFirstName(anyString())).willReturn(users);

        //when
        userService.getUserByFirstOrLastName(userDto.getFirstName());

        //then
        then(userRepository).should().findUsersByFirstName(anyString());
        assertThat(users).isNotNull();
    }

    @Test
    void getUserByLastName() {
        //given
        List<User> users = new ArrayList<>();
        given(userRepository.findUsersByLastName(anyString())).willReturn(users);

        //when
        userService.getUserByFirstOrLastName(userDto.getLastName());

        //then
        then(userRepository).should().findUsersByLastName(anyString());
        assertThat(users).isNotNull();
    }

    @Test
    void getUserByNoName() {
        //given
        List<User> users = new ArrayList<>();
        given(userRepository.findAll()).willReturn(users);

        //when
        userService.getUserByFirstOrLastName("");

        //then
        then(userRepository).should().findAll();
        assertThat(users).isNotNull();
    }
}