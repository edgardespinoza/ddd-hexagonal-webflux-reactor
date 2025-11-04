package com.example.demo.infrastructure.adapter.output.persistence;

import org.springframework.stereotype.Component;

import com.example.demo.domain.model.UserDomain;
import com.example.demo.domain.model.vo.user.UserEmail;
import com.example.demo.domain.model.vo.user.UserId;
import com.example.demo.domain.port.out.UserRepository;
import com.example.demo.infrastructure.adapter.output.persistence.mapper.UserEntityMapper;
import com.example.demo.infrastructure.adapter.output.persistence.repository.PostgresUserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PostgresUserAdapter implements UserRepository {

        private PostgresUserRepository postgresRepository;
        private UserEntityMapper userEntityMapper;

        public PostgresUserAdapter(PostgresUserRepository postgresRepository, UserEntityMapper userEntityMapper) {
                this.postgresRepository = postgresRepository;
                this.userEntityMapper = userEntityMapper;
        }

        @Override
        public Mono<UserDomain> findById(UserId id) {
                return postgresRepository.findById(id.id())
                                .map(userEntityMapper::toDomain);
        }

        @Override
        public Mono<UserDomain> save(UserDomain user) {
                return postgresRepository.save(userEntityMapper.toEntity(user))
                                .map(userEntityMapper::toDomain);

        }

        @Override
        public Mono<Void> deleteById(UserId id) {
                return postgresRepository.deleteById(id.id());
        }

        @Override
        public Flux<UserDomain> findAll() {
                return postgresRepository.findAll()
                                .map(userEntityMapper::toDomain);
        }

        @Override
        public Mono<UserDomain> findByEmail(UserEmail email) {
                return postgresRepository.findByEmailIgnoreCase(email.email())
                                .map(userEntityMapper::toDomain);
        }
}
