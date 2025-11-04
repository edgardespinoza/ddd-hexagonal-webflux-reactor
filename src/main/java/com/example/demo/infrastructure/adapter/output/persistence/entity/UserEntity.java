package com.example.demo.infrastructure.adapter.output.persistence.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record UserEntity(
        @Id UUID id,
        String name,
        String email) {
}
