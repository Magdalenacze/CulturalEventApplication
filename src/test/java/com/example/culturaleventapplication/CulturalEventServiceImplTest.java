package com.example.culturaleventapplication;

import com.example.culturaleventapplication.CulturalEvent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.CulturalEvent.repository.CulturalEventRepository;
import com.example.culturaleventapplication.CulturalEvent.service.CulturalEventService;
import com.example.culturaleventapplication.culturalevent.dto.CulturalEventDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CulturalEventServiceImplTest {

    @Autowired
    private CulturalEventService culturalEventService;

    @Autowired
    private CulturalEventRepository culturalEventRepository;

    @AfterEach
    void tearDown() {
        culturalEventRepository.deleteAll();
    }

    @Test
    public void should_create_new_event_successfully() {
        //given
        CulturalEventDto exampleDto = new CulturalEventDto(
                "Warsaw",
                "2026-05-26 12:00:00",
                "Concert");

        CulturalEventEntity referenceEntity = new CulturalEventEntity(
                exampleDto.getCity(),
                exampleDto.getEventDate(),
                exampleDto.getEventName());

        //when
        culturalEventService.createNewEvent(exampleDto);

        //then
        List<CulturalEventEntity> all = culturalEventRepository.findAll();
        assertThat(all).hasSize(1);
        CulturalEventEntity culturalEventEntity = all.get(0);
        assertThat(culturalEventEntity).isEqualTo(referenceEntity);
    }
}
