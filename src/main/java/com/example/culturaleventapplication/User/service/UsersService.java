package com.example.culturaleventapplication.User.service;

import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.entity.UserEntity;
import com.example.culturaleventapplication.User.mapper.Mappers;
import com.example.culturaleventapplication.User.mapper.MappersInterface;
import com.example.culturaleventapplication.User.repository.RepoUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UsersServiceInterface {

    private Mappers mappers;
    private RepoUsers repoUsers;

    @Autowired
    public UsersService(Mappers mappers, RepoUsers repoUsers) {
        this.mappers = mappers;
        this.repoUsers = repoUsers;
    }

    public void addUser(UserDto userDto) {
        UserEntity userEntity = mappers.toEnrtity(userDto);
        repoUsers.save(userEntity);
    }
}
