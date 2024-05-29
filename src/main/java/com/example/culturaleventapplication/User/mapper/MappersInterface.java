package com.example.culturaleventapplication.User.mapper;

import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.entity.UserEntity;

public interface MappersInterface {
    UserDto toDto(UserEntity userEntity);

    UserEntity toEnrtity(UserDto userDto);
}
