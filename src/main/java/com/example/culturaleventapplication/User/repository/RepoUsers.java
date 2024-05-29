package com.example.culturaleventapplication.User.repository;


import com.example.culturaleventapplication.User.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUsers extends JpaRepository<UserEntity, Long> {
}
