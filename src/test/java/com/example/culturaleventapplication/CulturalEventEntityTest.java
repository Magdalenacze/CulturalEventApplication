package com.example.culturaleventapplication;

import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.culturalevent.exception.CulturalEventException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CulturalEventEntityTest {

    @Test
    void should_create_new_event_for_the_current_date() {
        //given
        String city = "Warsaw";
        String eventDate = "2026-05-26 12:00:00";
        String eventName = "Concert";

        //when
        CulturalEventEntity culturalEventEntity = new CulturalEventEntity(
                city,
                eventDate,
                eventName);

        //then
        Assertions.assertThat(culturalEventEntity).isNotNull();
    }

    @Test
    void should_throw_an_exception_when_creating_a_new_event_for_a_backward_date() {
        //given
        String city = "Warsaw";
        String eventDate = "2023-05-26 12:00:00";
        String eventName = "Concert";

        //when
        Executable e = () -> new CulturalEventEntity(city, eventDate, eventName);

        //then
        assertThrows(CulturalEventException.class, e);
    }

    @Test
    void should_not_throw_an_exception_when_creating_a_new_event_for_the_current_date() {
        //given
        String city = "Warsaw";
        String eventDate = "2026-05-26 12:00:00";
        String eventName = "Concert";

        //when
        Executable e = () -> new CulturalEventEntity(city, eventDate, eventName);

        //then
        assertDoesNotThrow(e);
    }

    @Test
    void should_update_the_existing_entity_data_successfully() {
        //given
        CulturalEventEntity exampleEntity1 = new CulturalEventEntity(
                "Warsaw",
                "2026-05-26 12:00:00",
                "Concert");

        CulturalEventEntity exampleEntity2 = new CulturalEventEntity(
                "Warsaw",
                "2027-05-26 12:00:00",
                "Concert");

        //when
        exampleEntity1.updateEntityData(exampleEntity2);

        //then
        Assertions.assertThat(exampleEntity1).isEqualTo(exampleEntity2);
    }
}