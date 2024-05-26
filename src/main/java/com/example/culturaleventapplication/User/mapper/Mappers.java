package com.example.culturaleventapplication.User.mapper;

import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class Mappers implements MappersInterface {
    @Override
    public UserDto toDto(UserEntity userEntity){
        return new UserDto(userEntity.getNameUser(), userEntity.getCity(), userEntity.getEmailAdres());
    }
    @Override
    public UserEntity toEnrtity(UserDto userDto){
        return new UserEntity(userDto.getNameUser(), userDto.getCity(),userDto.getEmailAdres());
    }
}
