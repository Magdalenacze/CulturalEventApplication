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
    private CulturalEventService culturalEventService;


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

    @Test
    public void should_create_new_notification_successfully() {
        //given
        UserDto userDto = new UserDto(
                "Jason",
                "Warsaw",
                "jason@gmail.com");
        usersService.addUser(userDto);

        CulturalEventDto culturalEventDto = new CulturalEventDto(
                "Warsaw",
                "2026-05-26 12:00:00",
                "Concert");

        //when
        culturalEventService.createNewEvent(culturalEventDto);

        //then
        List<NotifyEntity> all = notifyRepo.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    public void should_not_allow_to_create_new_notification_for_user_from_city_other_than_city_of_the_event() {
        UserDto userDto = new UserDto(
                "Jason",
                "Warsaw",
                "jason@gmail.com");
        usersService.addUser(userDto);

        CulturalEventDto culturalEventDto = new CulturalEventDto(
                "Katowice",
                "2026-05-26 12:00:00",
                "Concert");

        //when
        culturalEventService.createNewEvent(culturalEventDto);

        //then
        List<NotifyEntity> all = notifyRepo.findAll();
        assertThat(all).hasSize(0);
    }

    @Test
    public void should_write_to_db_all_data_that_should_be_included_in_the_notification_successfully() {
        //given
        UserDto userDto = new UserDto(
                "Jason",
                "Warsaw",
                "jason@gmail.com");
        usersService.addUser(userDto);

        CulturalEventDto culturalEventDto = new CulturalEventDto(
                "Warsaw",
                "2026-05-26 12:00:00",
                "Concert");

        //when
        culturalEventService.createNewEvent(culturalEventDto);

        //then
        List<NotifyEntity> all = notifyRepo.findAll();
        assertThat(all.get(0).getUser()).isNotNull();
        assertThat(all.get(0).getEventid()).isNotNull();
        assertThat(all.get(0).getNameOfEvent()).isNotNull();
        assertThat(all.get(0).getEventCity()).isNotNull();
        assertThat(all.get(0).getEventDate()).isNotNull();
    }
}