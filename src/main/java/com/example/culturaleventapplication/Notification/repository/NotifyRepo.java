package com.example.culturaleventapplication.Notification.repository;

import com.example.culturaleventapplication.Notification.entity.NotifyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotifyRepo extends JpaRepository<NotifyEntity, Long> {
}

