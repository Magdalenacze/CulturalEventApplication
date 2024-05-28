package com.example.culturaleventapplication.culturalevent.service;

import com.example.culturaleventapplication.culturalevent.dto.CulturalEventDto;
import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.culturalevent.repository.CulturalEventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<CulturalEventDto> getAllEvents() {
        return culturalEventRepository.findAll()
                .stream()
                .map(e -> new CulturalEventDto(
                        e.getCity(),
                        e.getEventDate().toString(),
                        e.getEventName()))
                .toList();
    }

    @Override
    public List<CulturalEventDto> getAllEventsByCity(String city) {
        return culturalEventRepository.findAllByCityIgnoreCase(city)
                .stream()
                .map(e -> new CulturalEventDto(
                        e.getCity(),
                        e.getEventDate().toString(),
                        e.getEventName()))
                .toList();
    }
}
