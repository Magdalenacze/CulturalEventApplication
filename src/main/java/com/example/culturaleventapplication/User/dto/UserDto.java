package com.example.culturaleventapplication.User.dto;

import lombok.Getter;

@Getter
public class UserDto {
    private long id;
    private String nameUser;
    private String city;
    private String emailAdres;

    public UserDto(String userName, String city, String emailAdress) {
        this.nameUser = userName;
        this.city = city;
        this.emailAdres = emailAdress;
    }
}
