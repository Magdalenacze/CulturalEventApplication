package com.example.culturaleventapplication.culturalevent.repository;

import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CulturalEventRepository extends JpaRepository<CulturalEventEntity, Long> {
}
