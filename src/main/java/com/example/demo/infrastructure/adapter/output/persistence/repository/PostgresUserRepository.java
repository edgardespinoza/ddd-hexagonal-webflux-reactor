package com.example.demo.infrastructure.adapter.output.persistence.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.infrastructure.adapter.output.persistence.entity.UserEntity;

import reactor.core.publisher.Mono;

public interface PostgresUserRepository extends ReactiveCrudRepository<UserEntity, UUID> {

    @Query("SELECT * FROM users WHERE email ILIKE $1")
    Mono<UserEntity> findByEmailIgnoreCase(String email);

}