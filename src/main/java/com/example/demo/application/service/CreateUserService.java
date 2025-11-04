package com.example.demo.application.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.event.UserEvent;
import com.example.demo.domain.model.UserDomain;
import com.example.demo.domain.model.mapper.UserEventMapper;
import com.example.demo.domain.port.in.CreateUserUseCase;
import com.example.demo.domain.port.out.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class CreateUserService implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final UserEventMapper userEventMapper;

    public CreateUserService(UserRepository userRepository, ApplicationEventPublisher eventPublisher,
            UserEventMapper userEventMapper) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.userEventMapper = userEventMapper;
    }

    @Transactional
    public Mono<UserDomain> createUser(UserDomain user) {

        return userRepository.findByEmail(user.email())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT,
                                "User with email already exists"));
                    }
                    return userRepository.save(user).flatMap(savedUser -> {

                        UserEvent event = userEventMapper.toEvent(savedUser);

                        return Mono.fromRunnable(() -> eventPublisher.publishEvent(event))
                                .thenReturn(savedUser);
                    });
                });

    }
}
