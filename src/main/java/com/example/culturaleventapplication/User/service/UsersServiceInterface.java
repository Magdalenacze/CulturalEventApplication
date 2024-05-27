package com.example.culturaleventapplication.User.service;

import com.example.culturaleventapplication.User.dto.UserDto;

public interface UsersServiceInterface {
    void addUser(UserDto userDto);
    boolean isEmailAddressCorrect(String emailAddress);
}
