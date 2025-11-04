package com.example.demo.infrastructure.adapter.output.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.domain.model.UserDomain;
import com.example.demo.infrastructure.adapter.output.persistence.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    @Mapping(source = "id", target = "id.id")
    @Mapping(source = "name", target = "name.name")
    @Mapping(source = "email", target = "email.email")
    UserDomain toDomain(UserEntity entity);

    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "name.name", target = "name")
    @Mapping(source = "email.email", target = "email")
    UserEntity toEntity(UserDomain domain);
}
