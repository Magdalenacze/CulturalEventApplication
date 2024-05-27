package com.example.culturaleventapplication.User.controller;

import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UsersService usersService;

   /* @PostMapping("/useradd")
    public void addUser (@RequestBody UserDto userDto){
        usersService.addUser(userDto);
    }*/

    @PostMapping("/useradd")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        if (userDto.getEmailAdres() == null || !usersService.isEmailAddressCorrect(userDto.getEmailAdres())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email address");
        }
        usersService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
