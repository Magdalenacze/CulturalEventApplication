package com.example.culturaleventapplication;

import com.example.culturaleventapplication.culturalevent.dto.CulturalEventDto;
import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.culturalevent.exception.CulturalEventServiceException;
import com.example.culturaleventapplication.culturalevent.repository.CulturalEventRepository;
import com.example.culturaleventapplication.culturalevent.service.CulturalEventService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void should_get_all_events_successfully() {
        //given
        CulturalEventDto exampleDto1 = new CulturalEventDto(
                "Warsaw",
                "2026-05-26 12:00:00",
                "Concert");
        culturalEventService.createNewEvent(exampleDto1);

        CulturalEventDto exampleDto2 = new CulturalEventDto(
                "Cracow",
                "2027-05-26 13:00:00",
                "Concert");
        culturalEventService.createNewEvent(exampleDto2);

        //when
        List<CulturalEventDto> events = culturalEventService.getAllEvents();

        //then
        assertThat(events).hasSize(2);
    }

    @Test
    void should_get_all_events_by_city_successfully() {
        //given
        CulturalEventDto exampleDto1 = new CulturalEventDto(
                "Warsaw",
                "2026-05-26 12:00:00",
                "Concert");
        culturalEventService.createNewEvent(exampleDto1);

        CulturalEventDto exampleDto2 = new CulturalEventDto(
                "Cracow",
                "2027-05-26 13:00:00",
                "Concert");
        culturalEventService.createNewEvent(exampleDto2);

        CulturalEventDto exampleDto3 = new CulturalEventDto(
                "Warsaw",
                "2028-05-26 14:00:00",
                "Concert");
        culturalEventService.createNewEvent(exampleDto3);

        //when
        List<CulturalEventDto> eventsByCity = culturalEventService.getAllEventsByCity("Warsaw");

        //then
        assertThat(eventsByCity).hasSize(2);
    }

    @Test
    void should_not_allow_get_city_without_events() {
        //given
        CulturalEventDto exampleDto1 = new CulturalEventDto(
                "Warsaw",
                "2026-05-26 12:00:00",
                "Concert");
        culturalEventService.createNewEvent(exampleDto1);

        CulturalEventDto exampleDto2 = new CulturalEventDto(
                "Cracow",
                "2027-05-26 13:00:00",
                "Concert");
        culturalEventService.createNewEvent(exampleDto2);

        //when
        List<CulturalEventDto> eventsByCity = culturalEventService.getAllEventsByCity("Katowice");

        //then
        assertThat(eventsByCity).hasSize(0);
    }

    @Test
    public void should_delete_the_existing_event_successfully() {
        //given
        CulturalEventDto exampleDto = new CulturalEventDto(
                "Warsaw",
                "2026-05-26 12:00:00",
                "Concert");
        culturalEventService.createNewEvent(exampleDto);

        CulturalEventEntity culturalEventEntity = culturalEventRepository.findAll().get(0);

        //when
        culturalEventService.deleteEvent(culturalEventEntity.getEventId());

        //then
        List<CulturalEventEntity> all = culturalEventRepository.findAll();
        assertThat(all).hasSize(0);
    }

    @Test
    void should_throw_an_exception_when_deleting_a_non_existent_event() {
        //given
        CulturalEventDto exampleDto = new CulturalEventDto(
                "Warsaw",
                "2026-05-26 12:00:00",
                "Concert");
        culturalEventService.createNewEvent(exampleDto);

        //when
        Executable e = () -> culturalEventService.deleteEvent(-1l);

        //then
        CulturalEventServiceException culturalEventServiceException = assertThrows(
                CulturalEventServiceException.class, e);
        assertThat(culturalEventServiceException.getMessage()).contains(
                "The event was not deleted because it does not exist!");
    }

    @Test
    void should_not_throw_an_exception_when_deleting_an_existing_event() {
        //given
        CulturalEventDto exampleDto = new CulturalEventDto(
                "Warsaw",
                "2026-05-26 12:00:00",
                "Concert");
        culturalEventService.createNewEvent(exampleDto);

        CulturalEventEntity culturalEventEntity = culturalEventRepository.findAll().get(0);

        //when
        Executable e = () -> culturalEventService.deleteEvent(culturalEventEntity.getEventId());

        //then
        assertDoesNotThrow(e);
    }
}
