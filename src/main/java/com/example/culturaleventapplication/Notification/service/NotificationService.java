package com.example.culturaleventapplication.Notification.service;

import com.example.culturaleventapplication.Notification.dto.TechnicalNotifyDto;
import com.example.culturaleventapplication.Notification.entity.NotifyEntity;
import com.example.culturaleventapplication.Notification.repository.NotificationRepository;
import com.example.culturaleventapplication.User.entity.UserEntity;
import com.example.culturaleventapplication.User.repository.RepoUsers;
import com.example.culturaleventapplication.culturalevent.dto.CulturalEventDto;
import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.culturalevent.repository.CulturalEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NotificationService {

    private NotificationRepository notificationRepository;
    private RepoUsers repoUsers;
    private CulturalEventRepository culturalEventRepository;


    public void saveNotificationsToRepo(List<CulturalEventEntity> selectedEvents, List<UserEntity> userEntity) {
        for (int i = 0; i < selectedEvents.size(); i++) {
            NotifyEntity notifyTwoSave = new NotifyEntity(userEntity.getFirst(), selectedEvents.get(i).getTechnicalEventId(), selectedEvents.get(i).getEventName(), selectedEvents.get(i).getCity(), selectedEvents.get(i).getEventDate().toString());
            notificationRepository.save(notifyTwoSave);
        }

    }

    public List<TechnicalNotifyDto> getAllbyUserId(Long id) {
        UserEntity userToSearch = repoUsers.getReferenceById(id);
        return notificationRepository.findAll().stream().
                filter(e -> e.getUser().getId().equals(userToSearch.getId())).
                map(e -> new TechnicalNotifyDto(e.getNameOfEvent(), e.getEventCity())).
                toList();


    }

    private List<CulturalEventEntity> getAllEvents() {
        return culturalEventRepository.findAll();
    }

    private List<UserEntity> getAllusers() {
        return repoUsers.findAll();
    }

    private String findEventid(CulturalEventDto culturalEventDto) {
        List<CulturalEventEntity> newEvent = getAllEvents().stream().
                filter(e -> e.getEventName().equals(culturalEventDto.getEventName())).
                filter(e -> e.getCity().equals(culturalEventDto.getCity())).
                toList();
        return newEvent.getFirst().getEventId().toString();

    }
    private UUID findtechnicaltid(CulturalEventDto culturalEventDto) {
        List<CulturalEventEntity> newEvent = getAllEvents().stream().
                filter(e -> e.getEventName().equals(culturalEventDto.getEventName())).
                filter(e -> e.getCity().equals(culturalEventDto.getCity())).
                toList();
        return newEvent.getFirst().getTechnicalEventId();
    }

    private List<UserEntity> usersToNotify(CulturalEventDto culturalEventDto) {
        return getAllusers().stream().
                filter(e -> e.getCity().equals(culturalEventDto.getCity()))
                .toList();
    }

    public void createNotificationFromEventside (CulturalEventDto culturalEventDto) {
        List<UserEntity> users = usersToNotify(culturalEventDto);
        for (int i = 0; i < users.size(); i++) {
            NotifyEntity notifyToSave = new NotifyEntity(users.get(i), findtechnicaltid(culturalEventDto), culturalEventDto.getEventName(), culturalEventDto.getCity(), culturalEventDto.getEventDate());
            notificationRepository.save(notifyToSave);

        }
    }
}