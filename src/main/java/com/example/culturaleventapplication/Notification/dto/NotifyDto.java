package com.example.culturaleventapplication.Notification.dto;

import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.entity.UserEntity;
import lombok.Getter;

import java.util.UUID;
@Getter
public class NotifyDto {
    private Long id;
    private UserDto user;

    private UUID eventid;
    private String nameOfEvent;
    private String eventCity;

    public NotifyDto(UserDto user, UUID eventid, String nameOfEvent, String eventCity) {
        this.user = user;
        this.eventid = eventid;
        this.nameOfEvent = nameOfEvent;
        this.eventCity = eventCity;
    }
}

