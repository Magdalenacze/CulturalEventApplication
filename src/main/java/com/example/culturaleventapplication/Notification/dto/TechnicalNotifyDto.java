package com.example.culturaleventapplication.Notification.dto;

import lombok.Getter;

@Getter
public class TechnicalNotifyDto {
    private String nameOfEvent;
    private String eventCity;

    public TechnicalNotifyDto(String nameOfEvent, String eventCity) {
        this.nameOfEvent = nameOfEvent;
        this.eventCity = eventCity;
    }
}

