package com.example.culturaleventapplication.User.service;

import com.example.culturaleventapplication.Notification.service.NotificationService;
import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.entity.UserEntity;
import com.example.culturaleventapplication.User.mapper.Mappers;
import com.example.culturaleventapplication.User.repository.RepoUsers;
import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;
import com.example.culturaleventapplication.culturalevent.repository.CulturalEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UsersService implements UsersServiceInterface {

    private Mappers mappers;
    private RepoUsers repoUsers;
    private NotificationService notificationService;
    private CulturalEventRepository culturalEventRepository;

    public void addUser(UserDto userDto) throws EmailAlreadyExistsException {
        try {
            UserEntity userEntity = mappers.toEnrtity(userDto);
            repoUsers.save(userEntity);

        } catch (DataIntegrityViolationException e) {
            throw new EmailAlreadyExistsException("User with given email already exists:" + userDto.getEmailAdres());
        }
        String cityToSearch = userDto.getCity();
        String userEmail = userDto.getEmailAdres();
        notificationService.saveNotificationsToRepo(findEventsByCity(cityToSearch), getUserEntitybyEmail(userEmail));
    }

    public boolean isEmailAddressCorrect(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }


    public List<UserEntity> getAll() {
        return repoUsers.findAll();
    }

    private List<CulturalEventEntity> findEventsByCity(String city) {
        List<CulturalEventEntity> eventDtoList = culturalEventRepository.findAll();
        return eventDtoList.stream()
                .filter(event -> event.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    private List<UserEntity> getUserEntitybyEmail(String userEmail) {
        return getAll().stream().filter(e -> e.getEmailAddress().equals(userEmail)).toList();
    }

    public class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
    }
}
