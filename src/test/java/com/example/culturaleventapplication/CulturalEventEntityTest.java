package com.example.culturaleventapplication;

import com.example.culturaleventapplication.CulturalEvent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.culturalevent.exception.CulturalEventException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CulturalEventEntityTest {

    @Test
    void should_create_new_event_for_valid_input() {
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
    void should_not_create_new_event_for_invalid_input() {
        //given
        String city = "Warsaw";
        String eventDate = "2023-05-26 12:00:00";
        String eventName = "Concert";

        //when
        Executable e = () -> new CulturalEventEntity(city, eventDate, eventName);

        //then
        assertThrows(CulturalEventException.class, e);
    }
}
