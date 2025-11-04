package com.example.demo.domain.port.out;

import com.example.demo.domain.model.UserDomain;
import com.example.demo.domain.model.vo.user.UserEmail;
import com.example.demo.domain.model.vo.user.UserId;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<UserDomain> findById(UserId id);

    Mono<UserDomain> save(UserDomain user);

    Mono<Void> deleteById(UserId id);

    Flux<UserDomain> findAll();

    Mono<UserDomain> findByEmail(UserEmail email);

}
