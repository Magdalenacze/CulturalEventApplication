package com.example.culturaleventapplication.User.service;

import com.example.culturaleventapplication.Notification.service.NotifyService;
import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.entity.UserEntity;
import com.example.culturaleventapplication.User.mapper.Mappers;
import com.example.culturaleventapplication.User.repository.RepoUsers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class UsersService implements UsersServiceInterface {

    private Mappers mappers;
    private RepoUsers repoUsers;
    private NotifyService notifyService;

    public void addUser(UserDto userDto) {
        UserEntity userEntity = mappers.toEnrtity(userDto);
        repoUsers.save(userEntity);
        notifyService.createNotificationFromUserSide(userDto);
    }

    public boolean isEmailAddressCorrect(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
