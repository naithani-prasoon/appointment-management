package com.example.userservice.web.controller;

import com.example.userservice.service.UserService;
import com.example.userservice.service.UserServiceImpl;
import com.example.userservice.web.model.GenderEnum;
import com.example.userservice.web.model.User;
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

import static org.mockito.BDDMockito.given;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.reset;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id("1")
                .firstName("John")
                .lastName("Doe")
                .age(25)
                .emailAdress("JohnDoe@email.com")
                .phoneNumber("123456789")
                .build();
    }

    @AfterEach
    void tearDown() {
        reset(userService);
    }

    @Test
    void getUserById() throws Exception {
        given(userService.selectUser(any(String.class))).willReturn(user);

        MvcResult result = mockMvc.perform(get("/api/v1/user/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.emailAdress", is("JohnDoe@email.com")))
                .andExpect(jsonPath("$.phoneNumber", is("123456789")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void getAllUsers() throws Exception {
        given(userService.listUser()).willReturn(List.of(user));

        MvcResult result = mockMvc.perform(get("/api/v1/user/all"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void addNewUser() throws Exception {
        User newUser = User.builder().id("2").firstName("Henry").lastName("Cavil")
                .age(21).gender(GenderEnum.MALE).build();

        given(userService.createUser(any(User.class))).willReturn(newUser);
        String userJson = objectMapper.writeValueAsString(newUser);

        MvcResult result = mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("firstName", user.getFirstName())
                        .param("lastName", user.getLastName())
                        .param("age", String.valueOf(user.getAge())))
                .andExpect(status().isCreated())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void updateUser() throws Exception {
        User newUser = User.builder().id("2").firstName("Henry").lastName("Cavil")
                .age(21).gender(GenderEnum.MALE).build();

        given(userService.createUser(any(User.class))).willReturn(newUser);
        String userJson = objectMapper.writeValueAsString(newUser);

        MvcResult result = mockMvc.perform(put("/api/v1/user/" + user.getId())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", user.getFirstName())
                        .param("lastName", user.getLastName())
                        .param("age", String.valueOf(user.getAge())))
                        .andExpect(status().isOk())
                        .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    void deleteUser() throws Exception {
        given(userService.deleteUser(any(String.class))).willReturn(null);

        mockMvc.perform(delete("/api/v1/user/" + user.getId()))
                .andExpect(status().isOk());
    }
}