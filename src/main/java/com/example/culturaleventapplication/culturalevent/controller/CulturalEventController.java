package com.example.culturaleventapplication.culturalevent.controller;

import com.example.culturaleventapplication.culturalevent.dto.CulturalEventDto;
import com.example.culturaleventapplication.culturalevent.service.CulturalEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<CulturalEventDto> getAllEvents(@RequestParam(required = false) CulturalEventDto culturalEventDto) {
        return culturalEventService.getAllEvents();
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<CulturalEventDto> getAllEventsByCity(@RequestParam String city) {
        return culturalEventService.getAllEventsByCity(city);
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
