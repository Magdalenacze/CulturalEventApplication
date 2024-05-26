package com.example.culturaleventapplication.User.controller;

import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UsersService usersService;
    @PostMapping("/useradd")
    public void addUser (@RequestBody UserDto userDto){
        usersService.addUser(userDto);
    }
}
