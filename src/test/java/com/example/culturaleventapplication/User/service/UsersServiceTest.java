package com.example.culturaleventapplication.User.service;

import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.entity.UserEntity;
import com.example.culturaleventapplication.User.mapper.Mappers;
import com.example.culturaleventapplication.User.repository.RepoUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UsersServiceTest {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RepoUsers repoUsers;
    
    @Autowired
    
    private Mappers mappers;

    @BeforeEach
    void tearDown() {
        repoUsers.deleteAll();
    }

    @Test
    void is_user_added_propertly() {
        //GIVEN
        UserDto userDto = new UserDto("Jan Kowalski", "Warszawa", "jk@gmail.com");


        //WHEN
        usersService.addUser(userDto);
        List<UserEntity> all = repoUsers.findAll();
        UserDto toTest = mappers.toDto(all.getFirst());


        //THEN
        assertThat(toTest).isEqualTo(userDto);
        assertThat(all).hasSize(1);
        assertThat(toTest.getNameUser()).isEqualTo(userDto.getNameUser());
        assertThat(toTest.getCity()).isEqualTo(userDto.getCity());
        assertThat(toTest.getEmailAdres()).isEqualTo(userDto.getEmailAdres());
    }
}