package com.example.culturaleventapplication.Notification.dto;

import lombok.Getter;
import java.util.UUID;

@Getter
public class NotifyDto {
    private Long id;

    private UUID eventid;
    private String nameOfEvent;
    private String eventCity;

    public NotifyDto(UUID eventid, String nameOfEvent, String eventCity) {
        this.eventid = eventid;
        this.nameOfEvent = nameOfEvent;
        this.eventCity = eventCity;
    }
}