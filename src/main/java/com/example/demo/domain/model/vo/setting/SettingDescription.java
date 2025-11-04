package com.example.demo.domain.model.vo.setting;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils; // Necesario para verificar si tiene texto

public record SettingDescription(String value) {

    private static final int MAX_LENGTH = 255;
    private static final int MIN_LENGTH = 5;

    public SettingDescription {

        if (StringUtils.hasText(value)) {

            String trimmedValue = value.trim();
            int length = trimmedValue.length();

            Assert.isTrue(length >= MIN_LENGTH,
                    String.format("Setting description must be at least %d characters long if provided", MIN_LENGTH));
            Assert.isTrue(length <= MAX_LENGTH,
                    String.format("Setting description cannot exceed %d characters", MAX_LENGTH));

            value = trimmedValue;

        } else {
            value = null;
        }
    }
}