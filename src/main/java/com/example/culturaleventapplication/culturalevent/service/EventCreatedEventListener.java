package com.example.culturaleventapplication.culturalevent.service;

import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;

public interface EventCreatedEventListener {

    void notifyEventCreated(CulturalEventEntity culturalEventEntity);
}
