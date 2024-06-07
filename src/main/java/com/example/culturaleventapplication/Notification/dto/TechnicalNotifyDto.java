package com.example.culturaleventapplication.Notification.dto;

import lombok.Getter;

@Getter
public class TechnicalNotifyDto {
    private String nameOfEvent;
    private String eventCity;
    private String eventDate;

    public TechnicalNotifyDto(String nameOfEvent, String eventCity, String eventDate) {
        this.nameOfEvent = nameOfEvent;
        this.eventCity = eventCity;
        this.eventDate = eventDate;
    }
}