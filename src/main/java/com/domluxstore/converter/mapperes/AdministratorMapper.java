package com.domluxstore.converter.mapperes;

import com.domluxstore.converter.domain.administrator.AdministratorGetDto;
import com.domluxstore.converter.domain.administrator.AdministratorPostDto;

import com.domluxstore.domain.Administrator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {

    AdministratorMapper INSTANCE = Mappers.getMapper(AdministratorMapper.class);

    @Mapping(target = "user.name", source = "name")
    @Mapping(target = "user.phone", source = "phone")
    @Mapping(target = "user.email", source = "email")
    @Mapping(target = "user.password", source = "password")
    Administrator administratorPostDtoToAdministrator(AdministratorPostDto administratorPostDto);

    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phone", source = "user.phone")
    AdministratorGetDto administratorToAdministratorGetDto(Administrator administrator);
}
