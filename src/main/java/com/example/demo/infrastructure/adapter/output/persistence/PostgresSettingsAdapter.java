package com.example.demo.infrastructure.adapter.output.persistence;

import org.springframework.stereotype.Component;

import com.example.demo.domain.model.SettingDomain;
import com.example.demo.domain.port.out.SettingsRepository;
import com.example.demo.infrastructure.adapter.output.persistence.repository.PostgresSettingRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PostgresSettingsAdapter implements SettingsRepository {

    private PostgresSettingRepository postgresRepository;

    public PostgresSettingsAdapter(PostgresSettingRepository postgresRepository) {
        this.postgresRepository = postgresRepository;
    }

    @Override
    public Mono<SettingDomain> findByCode(String code) {
        return postgresRepository.findByCode(code).map(settingEntity -> new SettingDomain(
                settingEntity.id(),
                settingEntity.code(),
                settingEntity.description()));
    }

    @Override
    public Flux<SettingDomain> findAll() {
        return postgresRepository.findAll().map(settingEntity -> new SettingDomain(
                settingEntity.id(),
                settingEntity.code(),
                settingEntity.description()));
    }

}
