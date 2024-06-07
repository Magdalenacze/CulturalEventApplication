package com.example.culturaleventapplication.Notification;

import com.example.culturaleventapplication.Notification.entity.NotifyEntity;
import com.example.culturaleventapplication.Notification.repository.NotifyRepo;
import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.mapper.Mappers;
import com.example.culturaleventapplication.User.repository.RepoUsers;
import com.example.culturaleventapplication.User.service.UsersService;
import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.culturalevent.repository.CulturalEventRepository;
import org.junit.jupiter.api.AfterEach;
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
    private CulturalEventRepository culturalEventRepository;

    @Autowired
    private Mappers mappers;

    @AfterEach
    void tearDown() {
        notifyRepo.deleteAll();
        repoUsers.deleteAll();
        culturalEventRepository.deleteAll();
    }

    @Test
    void whether_notifications_are_correctly_created_and_saved_in_repository() {
        //GIVEN
        UserDto userDto = new UserDto("Jan Kowalski", "Warszawa", "jk@gmail.com");
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
}