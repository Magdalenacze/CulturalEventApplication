package com.example.culturaleventapplication.User.service;

import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.entity.UserEntity;
import com.example.culturaleventapplication.User.mapper.Mappers;
import com.example.culturaleventapplication.User.mapper.MappersInterface;
import com.example.culturaleventapplication.User.repository.RepoUsers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class UsersService implements UsersServiceInterface {

    private Mappers mappers;
    private RepoUsers repoUsers;

    public void addUser(UserDto userDto) {
        UserEntity userEntity = mappers.toEnrtity(userDto);
        repoUsers.save(userEntity);
    }
}
