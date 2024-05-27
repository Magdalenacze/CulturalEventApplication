package com.example.culturaleventapplication.User.controller;

import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDto validUserDto;
    private UserDto invalidUserDto;

    @BeforeEach
    public void setup() {
        validUserDto = new UserDto("jan Kowalski", "Warszawa", "jk@gmail.com");

        invalidUserDto = new UserDto("jan Kowalski", "Warszawa", "jkgmail.com");

    }

    @Test
    public void addUser_ShouldReturnStatusOk_WhenRequestIsValid() throws Exception {
        Mockito.doNothing().when(usersService).addUser(Mockito.any(UserDto.class));

        mockMvc.perform(post("/users/useradd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validUserDto)))
                .andExpect(status().isOk());

        Mockito.verify(usersService, Mockito.times(1)).addUser(Mockito.any(UserDto.class));
    }

    @Test
    public void addUser_ShouldReturnStatusBadRequest_WhenRequestIsInvalid() throws Exception {
        mockMvc.perform(post("/users/useradd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUserDto)))
                .andExpect(status().isBadRequest());

        Mockito.verify(usersService, Mockito.never()).addUser(Mockito.any(UserDto.class));
    }
}
