package com.example.demo.domain.model.vo.user;

import java.util.UUID;

import org.springframework.util.Assert;

public record UserId(UUID id) {
    public UserId {
        Assert.notNull(id, "id must not be null");

    }

    public UserId() {
        this(UUID.randomUUID());
    }
}
