package com.example.culturaleventapplication.Notification.entity;

import com.example.culturaleventapplication.CulturalEvent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.User.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "NOTIFICATIONS_Ent")
public class NotifyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userid;
    private UUID eventid;
    private String nameOfEvent;
    private String eventCity;

    public NotifyEntity(Long userid, UUID eventid, String nameOfEvent, String eventCity) {
        this.userid = userid;
        this.eventid = eventid;
        this.nameOfEvent = nameOfEvent;
        this.eventCity = eventCity;
    }
}

