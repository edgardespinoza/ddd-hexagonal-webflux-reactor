package com.example.demo.domain.event;

import java.util.UUID;

public record UserEvent(UUID id,
        String name,
        String email) {

}
