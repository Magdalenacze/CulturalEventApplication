package com.example.culturaleventapplication.culturalevent.controller;

import com.example.culturaleventapplication.culturalevent.dto.CulturalEventDto;
import com.example.culturaleventapplication.culturalevent.service.CulturalEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
