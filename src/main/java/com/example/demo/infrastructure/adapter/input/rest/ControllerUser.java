package com.example.demo.infrastructure.adapter.input.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.vo.user.UserId;
import com.example.demo.domain.port.in.CreateUserUseCase;
import com.example.demo.domain.port.in.FindUserUseCase;
import com.example.demo.infrastructure.adapter.input.rest.dto.UserCreateDto;
import com.example.demo.infrastructure.adapter.input.rest.dto.UserResponseDto;
import com.example.demo.infrastructure.adapter.input.rest.mapper.UserDtoMapping;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class ControllerUser {

        private CreateUserUseCase createUserUseCase;
        private FindUserUseCase findUserUseCase;
        private UserDtoMapping userDtoMapping;

        public ControllerUser(CreateUserUseCase createUserUseCase,
                        FindUserUseCase findUserUseCase,
                        UserDtoMapping UserDtoMapping) {
                this.createUserUseCase = createUserUseCase;
                this.findUserUseCase = findUserUseCase;
                this.userDtoMapping = UserDtoMapping;
        }

        @PostMapping
        public Mono<UserResponseDto> createUser(@RequestBody UserCreateDto userDto) {

                return createUserUseCase.createUser(
                                userDtoMapping.toDomain(userDto))
                                .map(userDtoMapping::toResponse);
        }

        @GetMapping(path = "/stream", produces = MediaType.APPLICATION_NDJSON_VALUE)
        public Flux<UserResponseDto> getAllUsers() {
                return findUserUseCase.findAllUsers()
                                .map(userDtoMapping::toResponse);
        }

        @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
        public Flux<UserResponseDto> getAllUsersNorma() {
                return findUserUseCase.findAllUsers()
                                .map(userDtoMapping::toResponse);
        }

        @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public Mono<UserResponseDto> getUser(@PathVariable UserId id) {
                return findUserUseCase.getUsers(id)
                                .map(userDtoMapping::toResponse);
        }

}
