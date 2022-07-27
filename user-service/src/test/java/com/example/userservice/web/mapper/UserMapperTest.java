package com.example.userservice.web.mapper;

import com.example.userservice.domain.User;
import com.example.userservice.model.UserDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    void userDtoToUser() {
        UserDto userDto = getValidUserDto();
        User user = mapper.userDtoToUser(userDto);

        assertThat(user.getFirstName()).isEqualTo(userDto.getFirstName());
        assertThat(user.getLastName()).isEqualTo(userDto.getLastName());
        assertThat(user.getAge()).isEqualTo(userDto.getAge());
        assertThat(user.getEmailAddress()).isEqualTo(userDto.getEmailAddress());
        assertThat(user.getPhoneNumber()).isEqualTo(userDto.getPhoneNumber());
    }

    @Test
    void userToUserDto() {
        User user = getValidUser();
        UserDto userDto = mapper.userToUserDto(user);

        assertThat(userDto.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(userDto.getLastName()).isEqualTo(user.getLastName());
        assertThat(userDto.getAge()).isEqualTo(user.getAge());
        assertThat(userDto.getEmailAddress()).isEqualTo(user.getEmailAddress());
        assertThat(userDto.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
    }

    User getValidUser() {
        return User.builder()
                .firstName("John")
                .lastName("Doe")
                .age(14)
                .emailAddress("JohnDoe@email.com")
                .phoneNumber("123456789")
                .build();
    }

    UserDto getValidUserDto() {
        return UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .age(14)
                .emailAddress("JohnDoe@email.com")
                .phoneNumber("123456789")
                .build();
    }
}