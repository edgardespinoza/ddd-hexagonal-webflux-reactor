package com.example.demo.domain.port.in;

import com.example.demo.domain.model.UserDomain;
import com.example.demo.domain.model.vo.user.UserId;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindUserUseCase {
    Flux<UserDomain> findAllUsers();

    Mono<UserDomain> getUsers(UserId id);
}
