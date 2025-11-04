package com.example.demo.domain.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.domain.event.UserEvent;
import com.example.demo.domain.model.UserDomain;

@Mapper(componentModel = "spring")
public interface UserEventMapper {

    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "name.name", target = "name")
    @Mapping(source = "email.email", target = "email")
    UserEvent toEvent(UserDomain userDomain);
}
