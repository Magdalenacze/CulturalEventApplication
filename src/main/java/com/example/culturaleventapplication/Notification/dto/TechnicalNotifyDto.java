package com.example.culturaleventapplication.Notification.dto;

import com.example.culturaleventapplication.User.dto.UserDto;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TechnicalNotifyDto {
    private String nameOfEvent;
    private String eventCity;

    public TechnicalNotifyDto(String nameOfEvent, String eventCity) {
        this.nameOfEvent = nameOfEvent;
        this.eventCity = eventCity;
    }
}

