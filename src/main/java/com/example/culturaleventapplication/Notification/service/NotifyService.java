package com.example.culturaleventapplication.Notification.service;

import com.example.culturaleventapplication.Notification.dto.TechnicalNotifyDto;
import com.example.culturaleventapplication.Notification.entity.NotifyEntity;
import com.example.culturaleventapplication.Notification.mapper.NotifyMapper;
import com.example.culturaleventapplication.Notification.repository.NotifyRepo;
import com.example.culturaleventapplication.User.entity.UserEntity;
import com.example.culturaleventapplication.User.repository.RepoUsers;
import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotifyService {

    private NotifyRepo notifyRepo;
    private RepoUsers repoUsers;
    private NotifyMapper notifyMapper;


    public void saveNotificationsToRepo(List<CulturalEventEntity> selectedEvents, List<UserEntity> userEntity) {
        for (int i = 0; i < selectedEvents.size(); i++) {
            NotifyEntity notifyTwoSave = new NotifyEntity(userEntity.getFirst(), selectedEvents.get(i).getTechnicalEventId(), selectedEvents.get(i).getEventName(), selectedEvents.get(i).getCity());
            notifyRepo.save(notifyTwoSave);
        }

    }

    public List<TechnicalNotifyDto> getAllbyUserId(Long id) {
        UserEntity userToSearch = repoUsers.getReferenceById(id);
        System.out.println("User to search: " + userToSearch.toString());

        List<NotifyEntity> allNotifications = notifyRepo.findAll();
        System.out.println("All notifications: " + allNotifications);

        return notifyRepo.findAll().stream().
                filter(e -> e.getUser().getId().equals(userToSearch.getId())).
                map(e -> new TechnicalNotifyDto(e.getNameOfEvent(), e.getEventCity())).
                toList();


    }
}