package com.example.demo.domain.port.out;

import com.example.demo.domain.model.SettingDomain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SettingsRepository {
    Mono<SettingDomain> findByCode(String code);

    Flux<SettingDomain> findAll();

}
