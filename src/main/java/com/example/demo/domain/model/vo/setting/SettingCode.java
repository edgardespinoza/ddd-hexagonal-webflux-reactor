package com.example.demo.domain.model.vo.setting;

import java.util.regex.Pattern;

import org.springframework.util.Assert;

public record SettingCode(String value) {

    private static final Pattern VALID_CODE_PATTERN = Pattern.compile("^[A-Z0-9_]*$");
    private static final int MAX_LENGTH = 50;

    public SettingCode {

        Assert.hasText(value, "Setting code is required");

        String trimmedValue = value.trim();

        Assert.isTrue(trimmedValue.length() <= MAX_LENGTH,
                String.format("Setting code cannot exceed %d characters", MAX_LENGTH));

        Assert.isTrue(VALID_CODE_PATTERN.matcher(trimmedValue).matches(),
                "Setting code must contain only uppercase letters, numbers, or underscores.");

        value = trimmedValue.toUpperCase();
    }
}