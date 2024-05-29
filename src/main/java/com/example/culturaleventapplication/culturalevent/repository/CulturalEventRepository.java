package com.example.culturaleventapplication.CulturalEvent.repository;


import com.example.culturaleventapplication.CulturalEvent.entity.CulturalEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CulturalEventRepository extends JpaRepository<CulturalEventEntity, Long> {
}
