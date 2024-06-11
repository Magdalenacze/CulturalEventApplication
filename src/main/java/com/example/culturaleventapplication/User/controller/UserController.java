package com.example.culturaleventapplication.User.controller;

import com.example.culturaleventapplication.Notification.dto.TechnicalNotifyDto;
import com.example.culturaleventapplication.Notification.service.NotificationService;
import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UsersService usersService;
    @Autowired
    private final NotificationService notificationService;


    @PostMapping("/useradd")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        try {
            if (userDto.getEmailAdres() == null || !usersService.isEmailAddressCorrect(userDto.getEmailAdres())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email address");
            }
            usersService.addUser(userDto);
            return ResponseEntity.status(HttpStatus.OK).body("ok");
        } catch (UsersService.EmailAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}/notifications")
    public List<TechnicalNotifyDto> setNotificationsFromRepo(@PathVariable Long id) {
        return notificationService.getAllbyUserId(id);
    }


}



