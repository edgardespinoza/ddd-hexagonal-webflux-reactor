package com.example.demo.domain.model.vo.user;

import org.springframework.util.Assert;

public record UserName(String name) {

    public UserName {

        Assert.hasText(name, "Name is required");

        String trimmedValue = name.trim();

        int length = trimmedValue.length();

        Assert.isTrue(length >= 2, "Name must be at least 2 characters long");
        Assert.isTrue(length <= 254, "Name cannot exceed 254 characters");

        name = trimmedValue;
    }
}