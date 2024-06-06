package com.example.culturaleventapplication.User.service;

import com.example.culturaleventapplication.User.entity.UserEntity;

import java.util.List;

public interface UserReadService {

    List<UserEntity> getUserByCity(String city);
}