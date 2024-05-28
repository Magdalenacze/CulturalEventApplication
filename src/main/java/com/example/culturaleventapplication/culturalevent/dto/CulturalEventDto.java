package com.example.culturaleventapplication.culturalevent.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class CulturalEventDto {

    private String city;
    private String eventDate;
    private String eventName;
}
