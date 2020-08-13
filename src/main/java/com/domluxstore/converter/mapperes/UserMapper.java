package com.domluxstore.converter.mapperes;

import com.domluxstore.converter.domain.administrator.AdministratorPutDto;
import com.domluxstore.converter.domain.cliente.ClientePutDto;
import com.domluxstore.converter.domain.user.UserPutDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "email", source = "email")
    UserPutDto administradorPutDtoToUserPutDto(AdministratorPutDto administratorPutDto);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "email", source = "email")
    UserPutDto clientPutDtoToUserPutDto(ClientePutDto clientePutDto);
}
