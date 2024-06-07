package com.example.culturaleventapplication.Notification.mapper;

import com.example.culturaleventapplication.Notification.dto.NotifyDto;
import com.example.culturaleventapplication.Notification.entity.NotifyEntity;
import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.entity.UserEntity;
import com.example.culturaleventapplication.User.mapper.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class NotifyMapper implements NotifyMapperInterface {

    public NotifyEntity toNotifyEntity(NotifyDto notifyDto, UserEntity userEntity) {
        return new NotifyEntity(userEntity, notifyDto.getEventid(), notifyDto.getNameOfEvent(), notifyDto.getEventCity(), notifyDto.getEventDate());
    }


    public NotifyDto toNotifyDto(NotifyEntity notifyEntity, UserDto userDto) {
        return new NotifyDto(userDto, notifyEntity.getEventid(), notifyEntity.getNameOfEvent(), notifyEntity.getEventCity(), notifyEntity.getEventDate());
    }
}