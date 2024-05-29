package com.example.culturaleventapplication.Notification.service;

import com.example.culturaleventapplication.CulturalEvent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.CulturalEvent.repository.CulturalEventRepository;
import com.example.culturaleventapplication.Notification.entity.NotifyEntity;
import com.example.culturaleventapplication.Notification.repository.NotifyRepo;
import com.example.culturaleventapplication.User.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotifyService {

    //private UsersService usersService;
    //private  CulturalEventService culturalEventService;
    private CulturalEventRepository culturalEventRepository;
    private NotifyRepo notifyRepo;

    public void createNotificationFromUserSide(UserDto userDto) {
        String cityToSearch = userDto.getCity();
        List<CulturalEventEntity> matchedEvents = findEventsByCity(cityToSearch);
        if (!matchedEvents.isEmpty()) {
            saveNotificationsToRepo(matchedEvents, userDto);
        }

    }

    private List<CulturalEventEntity> findEventsByCity(String city) {
        List<CulturalEventEntity> eventDtoList = culturalEventRepository.findAll();
        return eventDtoList.stream()
                .filter(event -> event.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    private void saveNotificationsToRepo(List<CulturalEventEntity> selectedEvents, UserDto userDto) {
        for (int i = 0; i < selectedEvents.size(); i++) {
            NotifyEntity notifyTwoSave = new NotifyEntity(userDto.getId(), selectedEvents.get(i).getTechnicalEventId(), selectedEvents.get(i).getEventName(), selectedEvents.get(i).getCity());
            notifyRepo.save(notifyTwoSave);
        }

    }
}
