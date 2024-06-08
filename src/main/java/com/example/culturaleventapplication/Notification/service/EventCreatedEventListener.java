package com.example.culturaleventapplication.Notification.service;

import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;

public interface EventCreatedEventListener {

    void notifyEventCreated(CulturalEventEntity culturalEventEntity);
}
