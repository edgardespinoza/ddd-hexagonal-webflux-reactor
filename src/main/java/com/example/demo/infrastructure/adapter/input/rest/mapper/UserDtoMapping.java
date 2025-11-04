package com.example.demo.infrastructure.adapter.input.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.domain.model.UserDomain;
import com.example.demo.infrastructure.adapter.input.rest.dto.UserCreateDto;
import com.example.demo.infrastructure.adapter.input.rest.dto.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserDtoMapping {

    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "name.name", target = "name")
    @Mapping(source = "email.email", target = "email")
    UserResponseDto toResponse(UserDomain userDomain);

    @Mapping(source = "name", target = "name.name")
    @Mapping(source = "email", target = "email.email")
    @Mapping(target = "id", ignore = true)
    UserDomain toDomain(UserCreateDto user);

}
