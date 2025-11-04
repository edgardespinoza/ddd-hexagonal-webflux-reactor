package com.example.demo.domain.model.vo.setting;

import java.util.UUID;

import org.springframework.util.Assert;

public record SettingId(UUID id) {
    public SettingId {
        Assert.notNull(id, "id must not be null");
    }

    public SettingId() {
        this(UUID.randomUUID());
    }
}