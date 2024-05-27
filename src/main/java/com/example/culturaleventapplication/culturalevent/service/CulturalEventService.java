package com.example.culturaleventapplication.culturalevent.service;

import com.example.culturaleventapplication.culturalevent.dto.CulturalEventDto;

import java.util.List;

public interface CulturalEventService {

    public void createNewEvent(CulturalEventDto culturalEventDto);

    List<CulturalEventDto> getAllEvents();

    List<CulturalEventDto> getAllEventsByCity(String city);
}
