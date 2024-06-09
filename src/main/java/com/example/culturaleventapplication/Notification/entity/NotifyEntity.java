package com.example.culturaleventapplication.Notification.entity;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private UUID eventid;
    private String nameOfEvent;
    private String eventCity;
    private String eventDate;

    public NotifyEntity(UserEntity user, UUID eventid, String nameOfEvent, String eventCity, String eventDate) {
        this.user = user;
        this.eventid = eventid;
        this.nameOfEvent = nameOfEvent;
        this.eventCity = eventCity;
        this.eventDate = eventDate;
    }
}

