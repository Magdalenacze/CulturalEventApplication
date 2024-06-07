package com.example.culturaleventapplication.culturalevent.controller;

import com.example.culturaleventapplication.culturalevent.dto.CulturalEventDto;
import com.example.culturaleventapplication.culturalevent.service.CulturalEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class CulturalEventController {

    private final CulturalEventService culturalEventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewEvent(@RequestBody CulturalEventDto culturalEventDto) {
        culturalEventService.createNewEvent(culturalEventDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CulturalEventDto> getAllEvents(@RequestParam(required = false) Optional<String> city) {
        return city
                .map(culturalEventService::getAllEventsByCity)
                .orElse(culturalEventService.getAllEvents());
    }

    @DeleteMapping("{id}")
    public void deleteEvent(@PathVariable("id") Long eventId) {
        culturalEventService.deleteEvent(eventId);
    }

    @PatchMapping("{id}")
    public void updateEvent(@PathVariable("id") Long eventId, @RequestBody CulturalEventDto culturalEventDto) {
        culturalEventService.updateEvent(eventId, culturalEventDto);
    }
}