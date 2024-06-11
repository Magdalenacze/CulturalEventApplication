package com.example.culturaleventapplication.culturalevent.service;

import com.example.culturaleventapplication.Notification.service.NotificationService;
import com.example.culturaleventapplication.culturalevent.dto.CulturalEventDto;
import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.culturalevent.exception.CulturalEventServiceException;
import com.example.culturaleventapplication.culturalevent.repository.CulturalEventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CulturalEventServiceImpl implements CulturalEventService {

    private final CulturalEventRepository culturalEventRepository;
    private final NotificationService notificationService;

    @Override
    //@Transactional
    public void createNewEvent(CulturalEventDto culturalEventDto) {
        culturalEventRepository.save(new CulturalEventEntity(
                culturalEventDto.getCity(),
                culturalEventDto.getEventDate(),
                culturalEventDto.getEventName()));
        notificationService.createNotificationFromEventside(culturalEventDto);
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

    @Override
    @Transactional
    public void deleteEvent(Long eventId) {
        Optional<CulturalEventEntity> culturalEventEntity = culturalEventRepository.findById(eventId);
        culturalEventEntity.orElseThrow(() -> new CulturalEventServiceException(
                "The event was not deleted because it does not exist!"));
        culturalEventRepository.deleteById(eventId);
    }

    @Override
    @Transactional
    public void updateEvent(Long eventId, CulturalEventDto culturalEventDto) {
        Optional<CulturalEventEntity> culturalEventEntity = culturalEventRepository.findById(eventId);
        culturalEventEntity.orElseThrow(() -> new CulturalEventServiceException(
                "The event was not updated because it does not exist!"));
        culturalEventEntity.get().updateEntityData(new CulturalEventEntity(
                culturalEventDto.getCity(),
                culturalEventDto.getEventDate(),
                culturalEventDto.getEventName()));
        culturalEventRepository.save(culturalEventEntity.get());
    }
}