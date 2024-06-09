package com.example.culturaleventapplication.Notification.service;

import com.example.culturaleventapplication.Notification.dto.TechnicalNotifyDto;
import com.example.culturaleventapplication.Notification.entity.NotifyEntity;
import com.example.culturaleventapplication.Notification.repository.NotifyRepo;
import com.example.culturaleventapplication.User.entity.UserEntity;
import com.example.culturaleventapplication.User.repository.RepoUsers;
import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.culturalevent.service.EventCreatedEventListener;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotifyService implements EventCreatedEventListener, NotifyServiceInterface {

    private NotifyRepo notifyRepo;
    private RepoUsers repoUsers;


    public void saveNotificationsToRepo(List<CulturalEventEntity> selectedEvents, List<UserEntity> userEntity) {
        for (int i = 0; i < selectedEvents.size(); i++) {
            NotifyEntity notifyTwoSave = new NotifyEntity(
                    userEntity.getFirst(),
                    selectedEvents.get(i).getTechnicalEventId(),
                    selectedEvents.get(i).getEventName(),
                    selectedEvents.get(i).getCity(),
                    selectedEvents.get(i).getEventDate().toString());
            notifyRepo.save(notifyTwoSave);
        }
    }

    public List<TechnicalNotifyDto> getAllbyUserId(Long id) {
        UserEntity userToSearch = repoUsers.getReferenceById(id);
        return notifyRepo.findAll().stream().
                filter(e -> e.getUser().getId().equals(userToSearch.getId())).
                map(e -> new TechnicalNotifyDto(e.getNameOfEvent(), e.getEventCity(), e.getEventDate())).
                toList();
    }

    @Override
    public void createNotification(CulturalEventEntity culturalEventEntity) {
        List<UserEntity> userByCityList = repoUsers.findAllUserByCity(culturalEventEntity.getCity());
        userByCityList
                .stream()
                .forEach(e -> notifyRepo.save(new NotifyEntity(e,
                        culturalEventEntity.getTechnicalEventId(),
                        culturalEventEntity.getEventName(),
                        culturalEventEntity.getCity(),
                        culturalEventEntity.getEventDate().toString())));
    }

    @Override
    public void notifyEventCreated(CulturalEventEntity culturalEventEntity) {
        createNotification(culturalEventEntity);
    }
}