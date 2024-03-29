package com.example.userservice.web.controller;

import com.example.userservice.model.GenderEnum;
import com.example.userservice.model.UserDto;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

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

    @AfterEach
    void tearDown() {
        reset(userService);
    }

    @Test
    void getUserById() throws Exception {
        given(userService.selectUser("1")).willReturn(userDto);

        MvcResult result = mockMvc.perform(get("/api/v1/user/" + userDto.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.emailAddress", is("JohnDoe@email.com")))
                .andExpect(jsonPath("$.phoneNumber", is("123456789")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void getAllUsers() throws Exception {
        given(userService.listUser()).willReturn(List.of(userDto));

        MvcResult result = mockMvc.perform(get("/api/v1/user/all"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void addNewUser() throws Exception {
        UserDto newUserDto = UserDto.builder().id("2").firstName("Henry").lastName("Cavil")
                .age(21).gender(GenderEnum.MALE).emailAddress("test@email.com").build();

        given(userService.createUser(any(UserDto.class))).willReturn(newUserDto);
        String userJson = objectMapper.writeValueAsString(newUserDto);

        MvcResult result = mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("firstName", userDto.getFirstName())
                        .param("lastName", userDto.getLastName())
                        .param("emailAddress", newUserDto.getEmailAddress())
                        .param("age", String.valueOf(userDto.getAge())))
                .andExpect(status().isCreated())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void updateUser() throws Exception {
        UserDto newUserDto = UserDto.builder().id("2").firstName("Henry").lastName("Cavil")
                .age(21).gender(GenderEnum.MALE).emailAddress("test@email.com").build();

        given(userService.createUser(any(UserDto.class))).willReturn(newUserDto);
        String userJson = objectMapper.writeValueAsString(newUserDto);

        MvcResult result = mockMvc.perform(put("/api/v1/user/" + userDto.getId())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", userDto.getFirstName())
                        .param("lastName", userDto.getLastName())
                        .param("emailAddress", newUserDto.getEmailAddress())
                        .param("age", String.valueOf(userDto.getAge())))
                        .andExpect(status().isOk())
                        .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    void deleteUser() throws Exception {

        mockMvc.perform(delete("/api/v1/user/" + userDto.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void getUserByFirstOrLastName() throws Exception {
        given(userService.getUserByFirstOrLastName(any(String.class))).willReturn(List.of(userDto));

        mockMvc.perform(get("/api/v1/user/")
                .param("name", userDto.getFirstName()+ " " + userDto.getLastName()))
                .andExpect(status().isOk());
    }
}