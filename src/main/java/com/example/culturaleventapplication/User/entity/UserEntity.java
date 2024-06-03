package com.example.culturaleventapplication.User.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "APPLICATION_USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"emailAddress"})
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameUser;
    private String city;
    private String emailAddress;

    public UserEntity(String nameUser, String city, String emailAdress) {
        this.nameUser = nameUser;
        this.city = city;
        this.emailAddress = emailAdress;
    }
}
