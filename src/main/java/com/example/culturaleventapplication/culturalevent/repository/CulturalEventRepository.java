package com.example.culturaleventapplication.culturalevent.repository;

import com.example.culturaleventapplication.culturalevent.entity.CulturalEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CulturalEventRepository extends JpaRepository<CulturalEventEntity, Long> {

    List<CulturalEventEntity> findAllByCityIgnoreCase(String city);
}