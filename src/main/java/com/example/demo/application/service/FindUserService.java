package com.example.demo.application.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.model.UserDomain;
import com.example.demo.domain.model.vo.user.UserId;
import com.example.demo.domain.port.in.FindUserUseCase;
import com.example.demo.domain.port.out.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FindUserService implements FindUserUseCase {

    private final UserRepository userRepository;

    public FindUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Flux<UserDomain> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<UserDomain> getUsers(UserId id) {
        return userRepository.findById(id);
    }

}
