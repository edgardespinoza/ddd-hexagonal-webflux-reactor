package com.example.demo.domain.model.vo.user;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.Assert;

public record UserEmail(String email) {

    private static final EmailValidator validator = EmailValidator.getInstance(true);

    public UserEmail {

        Assert.hasText(email, "Email is required");

        Assert.isTrue(email.length() <= 254, "Email cannot exceed 254 characters");

        Assert.isTrue(validator.isValid(email), "Email must be valid");
    }
}