package com.domluxstore.service.map;

import com.domluxstore.converter.domain.cliente.ClientePutDto;
import com.domluxstore.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ClientService {

    Client save(Client client);

    Client update(Client client,ClientePutDto clientePutDto);

    Page<Client> listAll(Pageable pageable);

    Page<Client> findAllByPhone(String phone,Pageable pageable);

    Page<Client> findAllByEmail(String email,Pageable pageable);

    Page<Client> findAllByName(String name,Pageable pageable);

    Page<Client> filterSerach(Map<String,String> filtros, Pageable pageable);
}
