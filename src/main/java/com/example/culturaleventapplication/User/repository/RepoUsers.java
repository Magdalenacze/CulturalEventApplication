package com.example.culturaleventapplication.User.repository;

import com.example.culturaleventapplication.User.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoUsers extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByEmailAddress(String emailAddress);
}
