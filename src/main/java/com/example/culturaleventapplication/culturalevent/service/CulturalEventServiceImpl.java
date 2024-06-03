package com.example.culturaleventapplication.CulturalEvent.service;

import com.example.culturaleventapplication.CulturalEvent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.CulturalEvent.repository.CulturalEventRepository;
import com.example.culturaleventapplication.culturalevent.dto.CulturalEventDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CulturalEventServiceImpl implements CulturalEventService {

    private final CulturalEventRepository culturalEventRepository;

    @Override
    @Transactional
    public void createNewEvent(CulturalEventDto culturalEventDto) {
        culturalEventRepository.save(new CulturalEventEntity(
                culturalEventDto.getCity(),
                culturalEventDto.getEventDate(),
                culturalEventDto.getEventName()));
    }
}
