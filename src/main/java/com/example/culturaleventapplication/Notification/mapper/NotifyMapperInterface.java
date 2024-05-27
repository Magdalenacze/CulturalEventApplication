package com.example.culturaleventapplication.Notification.mapper;

import com.example.culturaleventapplication.Notification.dto.NotifyDto;
import com.example.culturaleventapplication.Notification.entity.NotifyEntity;

public interface NotifyMapperInterface {
    NotifyEntity toNotifyEntity(NotifyDto notifyDto);

    NotifyDto toNotifyDto(NotifyEntity notifyEntity);
}
