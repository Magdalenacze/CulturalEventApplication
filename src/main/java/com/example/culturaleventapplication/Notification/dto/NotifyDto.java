package com.example.culturaleventapplication.Notification.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;
@Getter
public class NotifyDto {
    private Long id;
    private Long userid;
    private UUID eventid;
    private String nameOfEvent;
    private String eventCity;

    public NotifyDto(Long userid, UUID eventid, String nameOfEvent, String eventCity) {
        this.userid = userid;
        this.eventid = eventid;
        this.nameOfEvent = nameOfEvent;
        this.eventCity = eventCity;
    }
}
