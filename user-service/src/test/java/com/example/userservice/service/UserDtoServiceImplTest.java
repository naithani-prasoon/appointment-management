package com.example.userservice.service;

import com.example.userservice.repository.UserRepository;
import com.example.userservice.web.exception.NotFoundException;
import com.example.userservice.model.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserDtoServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    UserDto userDto;

    @BeforeEach
    void setUp() {
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
        given(userRepository.findUserById("1")).willReturn(userDto);

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
        given(userRepository.save(any(UserDto.class))).willReturn(userDto);

        //when
        UserDto savedUserDto = userService.createUser(new UserDto());

        //then
        then(userRepository).should().save(any(UserDto.class));
        assertThat(savedUserDto).isNotNull();
    }

    @Test
    void updateUser() {
        //given
        given(userRepository.save(any(UserDto.class))).willReturn(userDto);
        //when
        UserDto savedUserDto = userService.updateUser("1", userDto);

        //then
        then(userRepository).should().save(any(UserDto.class));
        assertThat(savedUserDto).isNotNull();
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
        List<UserDto> userDtos = new ArrayList<UserDto>();
        given(userRepository.findAll()).willReturn(userDtos);

        //when
        userService.listUser();

        //then
        then(userRepository).should().findAll();
        assertThat(userDtos).isNotNull();
    }

    @Test
    void getUserByFirstAndLastName() {
        //given
        List<UserDto> userDtos = new ArrayList<UserDto>();
        given(userRepository.findUsersByFirstNameAndLastName(anyString(), anyString())).willReturn(userDtos);

        //when
        userService.getUserByFirstOrLastName(userDto.getFirstName() + " " + userDto.getLastName());

        //then
        then(userRepository).should().findUsersByFirstNameAndLastName(anyString(), anyString());
        assertThat(userDtos).isNotNull();

    }

    @Test
    void getUserByFirstName() {
        //given
        List<UserDto> userDtos = new ArrayList<UserDto>();
        given(userRepository.findUsersByFirstName(anyString())).willReturn(userDtos);

        //when
        userService.getUserByFirstOrLastName(userDto.getFirstName());

        //then
        then(userRepository).should().findUsersByFirstName(anyString());
        assertThat(userDtos).isNotNull();
    }

    @Test
    void getUserByLastName() {
        //given
        List<UserDto> userDtos = new ArrayList<UserDto>();
        given(userRepository.findUsersByLastName(anyString())).willReturn(userDtos);

        //when
        userService.getUserByFirstOrLastName(userDto.getLastName());

        //then
        then(userRepository).should().findUsersByLastName(anyString());
        assertThat(userDtos).isNotNull();
    }

    @Test
    void getUserByNoName() {
        //given
        List<UserDto> userDtos = new ArrayList<UserDto>();
        given(userRepository.findAll()).willReturn(userDtos);

        //when
        userService.getUserByFirstOrLastName("");

        //then
        then(userRepository).should().findAll();
        assertThat(userDtos).isNotNull();
    }
}