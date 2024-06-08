package com.example.culturaleventapplication.Notification.mapper;

import com.example.culturaleventapplication.Notification.dto.NotifyDto;
import com.example.culturaleventapplication.Notification.entity.NotifyEntity;
import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class NotifyMapper implements NotifyMapperInterface {

    public NotifyEntity toNotifyEntity(NotifyDto notifyDto, UserEntity userEntity) {
        return new NotifyEntity(userEntity, notifyDto.getEventid(), notifyDto.getNameOfEvent(), notifyDto.getEventCity());
    }


    public NotifyDto toNotifyDto(NotifyEntity notifyEntity, UserDto userDto) {
        return new NotifyDto(notifyEntity.getEventid(), notifyEntity.getNameOfEvent(), notifyEntity.getEventCity());
    }
}