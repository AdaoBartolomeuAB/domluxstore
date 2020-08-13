package com.domluxstore.converter.mapperes;

import com.domluxstore.converter.domain.cliente.ClienteGetDto;
import com.domluxstore.converter.domain.cliente.ClientePostDto;
import com.domluxstore.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "user.name", source = "name")
    @Mapping(target = "user.phone", source = "phone")
    @Mapping(target = "user.email", source = "email")
    @Mapping(target = "user.password", source = "password")
    Client clientPostDtoToClient(ClientePostDto clientePostDto);

    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phone", source = "user.phone")
    ClienteGetDto clientToClientGetDto(Client client);
}
