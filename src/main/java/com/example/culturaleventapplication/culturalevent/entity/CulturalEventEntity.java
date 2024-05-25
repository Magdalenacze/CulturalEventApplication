package com.example.culturaleventapplication.culturalevent.entity;

import com.example.culturaleventapplication.culturalevent.exception.CulturalEventException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CulturalEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    private UUID technicalEventId;
    private String city;
    private Timestamp eventDate;
    private String eventName;

    public CulturalEventEntity(String city, String eventDate, String eventName) {
        this.eventDate = formatDate(eventDate);
        validateDate(this.eventDate);
        this.technicalEventId = UUID.randomUUID();
        this.city = city;
        this.eventName = eventName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CulturalEventEntity that)) return false;
        return Objects.equals(city, that.city) && Objects.equals(eventDate, that.eventDate) && Objects.equals(eventName, that.eventName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, eventDate, eventName);
    }

    private Timestamp formatDate(String eventDate) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(eventDate));
        return Timestamp.valueOf(localDateTime);
    }

    private void validateDate(Timestamp eventDate) {
        if (eventDate.before(new Date())) {
            throw new CulturalEventException("You cannot create a new backdated event!");
        }
    }
}
