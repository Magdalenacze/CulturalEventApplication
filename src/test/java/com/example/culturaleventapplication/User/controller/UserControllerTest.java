package com.example.culturaleventapplication.User.controller;

import com.example.culturaleventapplication.Notification.dto.NotifyDto;
import com.example.culturaleventapplication.Notification.dto.TechnicalNotifyDto;
import com.example.culturaleventapplication.Notification.entity.NotifyEntity;
import com.example.culturaleventapplication.Notification.repository.NotifyRepo;
import com.example.culturaleventapplication.Notification.service.NotifyService;
import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.entity.UserEntity;
import com.example.culturaleventapplication.User.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.reflect.Array.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private MockMvc mockMvc;
    @Mock
    private UsersService usersService;

    @Mock
    private NotifyService notifyService;
    @Mock
    private NotifyRepo notifyRepo;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser_InvalidEmail_ReturnsBadRequest() {
        // GIVEN
        UserDto userDto = new UserDto("Jan Kowalski", "Warszawa", "jk@gmail.com");
        userDto.setEmailAdres("bzdura");

        when(usersService.isEmailAddressCorrect(userDto.getEmailAdres())).thenReturn(false);


        // WHEN
        ResponseEntity<String> response = userController.addUser(userDto);


        // THEN
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid email address", response.getBody());
        verify(usersService, never()).addUser(any(UserDto.class));
    }

    @Test
    void addUser_ValidEmail_ReturnsOk() {
        // GIVEN
        UserDto userDto = new UserDto("Jan Kowalski", "Warszawa", "jk@gmail.com");


        // WHEN
        when(usersService.isEmailAddressCorrect(userDto.getEmailAdres())).thenReturn(true);
        ResponseEntity<String> response = userController.addUser(userDto);


        // THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ok", response.getBody());
        verify(usersService, times(1)).addUser(userDto);
    }

}
