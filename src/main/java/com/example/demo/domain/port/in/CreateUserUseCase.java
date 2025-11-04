package com.example.demo.domain.port.in;

import com.example.demo.domain.model.UserDomain;

import reactor.core.publisher.Mono;

public interface CreateUserUseCase {
    Mono<UserDomain> createUser(UserDomain user);
}
