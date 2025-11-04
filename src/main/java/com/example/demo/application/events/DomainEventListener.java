package com.example.demo.application.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.demo.domain.event.UserEvent;

import reactor.core.publisher.Mono;

@Component
public class DomainEventListener {

    @EventListener
    public Mono<Void> handle(UserEvent event) {
        return Mono.fromRunnable(() -> System.out.println("Domain event received: " + event))
                .then();

    }
}
