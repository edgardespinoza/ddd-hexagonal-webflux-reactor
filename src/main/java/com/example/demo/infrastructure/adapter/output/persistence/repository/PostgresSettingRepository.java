package com.example.demo.infrastructure.adapter.output.persistence.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.example.demo.domain.model.vo.setting.SettingId;
import com.example.demo.infrastructure.adapter.output.persistence.entity.SettingEntity;

import reactor.core.publisher.Mono;

public interface PostgresSettingRepository extends R2dbcRepository<SettingEntity, SettingId> {
    Mono<SettingEntity> findByCode(String code);

}
