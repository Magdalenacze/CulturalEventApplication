package com.example.culturaleventapplication.User.dto;

import lombok.Getter;

import java.util.Objects;

@Getter
public class UserDto {
    private long id;
    private String nameUser;
    private String city;
    private String emailAdres;

    public UserDto(String nameUser, String city, String emailAdress) {
        this.nameUser = nameUser;
        this.city = city;
        this.emailAdres = emailAdress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(nameUser, userDto.nameUser) && Objects.equals(city, userDto.city) && Objects.equals(emailAdres, userDto.emailAdres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameUser, city, emailAdres);
    }
}
