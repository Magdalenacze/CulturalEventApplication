package com.example.culturaleventapplication.Notification;

import com.example.culturaleventapplication.Notification.entity.NotifyEntity;
import com.example.culturaleventapplication.Notification.repository.NotifyRepo;
import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.mapper.Mappers;
import com.example.culturaleventapplication.User.repository.RepoUsers;
import com.example.culturaleventapplication.User.service.UsersService;
import com.example.culturaleventapplication.culturalevent.dto.CulturalEventDto;
import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.culturalevent.repository.CulturalEventRepository;
import com.example.culturaleventapplication.culturalevent.service.CulturalEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class NotifyServiceTest {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RepoUsers repoUsers;

    @Autowired
    private NotifyRepo notifyRepo;

    @Autowired
    private CulturalEventService culturalEventService;

    @Autowired
    private  CulturalEventRepository culturalEventRepository;

    @Autowired
    private Mappers mappers;

    @BeforeEach
    void tearDown() {
        notifyRepo.deleteAll();
        culturalEventRepository.deleteAll();
    }


    @Test
    void whether_notifications_are_correctly_created_and_saved_in_repository_when_user_is_added() {
        //GIVEN
        UserDto userDto = new UserDto("Jan Sowalski", "Warszawa", "jk2@gmail.com");
        CulturalEventEntity event = new CulturalEventEntity("Warszawa", "2024-12-01 12:45:00", "Spotkania przy ognisku");
        culturalEventRepository.save(event);

        //WHEN
        usersService.addUser(userDto);

        List<NotifyEntity> notifications = notifyRepo.findAll();

        //THEN
        assertFalse(notifications.isEmpty());
        assertThat(notifications.get(0).getUser().getCity()).isEqualTo(userDto.getCity());
        assertThat(notifications.get(0).getUser().getNameUser()).isEqualTo(userDto.getNameUser());
        assertThat(notifications.get(0).getUser().getEmailAddress()).isEqualTo(userDto.getEmailAdres());
        assertThat(notifications.get(0).getEventCity().equals(userDto.getCity()));
    }
    @Test
    void whether_notifications_are_correctly_created_and_saved_in_repository_when_event_is_added(){
        //GIVEN
        UserDto userDto = new UserDto("Jan Kowalski", "Warszawa", "jk@gmail.com");
        CulturalEventDto event2 = new CulturalEventDto("Warszawa", "2024-12-01 12:45:00", "Spotkania przy ognisku");

        usersService.addUser(userDto);
        //WHEN

        culturalEventService.createNewEvent(event2);
        List<NotifyEntity> notifications = notifyRepo.findAll();

        //THEN
        assertFalse(notifications.isEmpty());
        assertThat(notifications.get(0).getUser().getCity()).isEqualTo(userDto.getCity());
        assertThat(notifications.get(0).getUser().getNameUser()).isEqualTo(userDto.getNameUser());
        assertThat(notifications.get(0).getUser().getEmailAddress()).isEqualTo(userDto.getEmailAdres());
        assertThat(notifications.get(0).getEventCity().equals(userDto.getCity()));
    }
}