package com.example.culturaleventapplication.culturalevent.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CulturalEventDto {

    private String city;
    private String eventDate;
    private String eventName;
}
