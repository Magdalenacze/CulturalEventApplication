package com.example.culturaleventapplication.User.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@Table(name = "APPLICATION_USERS")
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nameUser;
    private String city;
    private String emailAdres;

    public UserEntity(String userName, String city, String emailAdress) {
        this.nameUser = userName;
        this.city = city;
        this.emailAdres = emailAdress;
    }
}
