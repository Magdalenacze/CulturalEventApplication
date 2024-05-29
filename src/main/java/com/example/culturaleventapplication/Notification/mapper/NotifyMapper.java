package com.example.culturaleventapplication.Notification.mapper;

import com.example.culturaleventapplication.Notification.dto.NotifyDto;
import com.example.culturaleventapplication.Notification.entity.NotifyEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotifyMapper implements NotifyMapperInterface {
    @Override
    public NotifyEntity toNotifyEntity(NotifyDto notifyDto) {
        return new NotifyEntity(notifyDto.getUserid(), notifyDto.getEventid(), notifyDto.getNameOfEvent(), notifyDto.getEventCity());
    }

    @Override
    public NotifyDto toNotifyDto(NotifyEntity notifyEntity) {
        return new NotifyDto(notifyEntity.getUserid(), notifyEntity.getEventid(), notifyEntity.getNameOfEvent(), notifyEntity.getEventCity());
    }
}