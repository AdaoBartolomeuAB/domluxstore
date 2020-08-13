package com.domluxstore.service;

import com.domluxstore.converter.domain.cliente.ClientePutDto;
import com.domluxstore.converter.domain.user.UserPutDto;
import com.domluxstore.converter.mapperes.UserMapper;
import com.domluxstore.domain.Client;
import com.domluxstore.domain.User;
import com.domluxstore.exception.BadRequestException;
import com.domluxstore.repository.ClientRepository;
import com.domluxstore.service.map.ClientService;
import com.domluxstore.service.map.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
@Transactional
public class ClienteServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public ClienteServiceImpl(ClientRepository clientRepository, UserMapper userMapper, UserService userService) {
        this.clientRepository = clientRepository;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Override
    public Client save(Client client) {
        User user = userService.save(client.getUser(),"ROLE_CLIENT");
        client.setUser(user);
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client, ClientePutDto clientePutDto) {

        UserPutDto userPutDto = userMapper.clientPutDtoToUserPutDto(clientePutDto);

        User user1 = userService.update(client.getUser().getId(),userPutDto);

        client.setUser(user1);
        return clientRepository.save(client);
    }

    @Override
    public Page<Client> findAllByPhone(String phone,Pageable pageable) {
        return clientRepository.findAllByUser_PhoneContaining(phone,pageable);
    }

    @Override
    public Page<Client> findAllByEmail(String email,Pageable pageable) {
        return clientRepository.findAllByUser_EmailContaining(email,pageable);
    }

    @Override
    public Page<Client> findAllByName(String name,Pageable pageable) {
        return clientRepository.findAllByUser_NameContaining(name,pageable);
    }

    @Override
    public Page<Client> listAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Page<Client> filterSerach(Map<String,String> filtros, Pageable pageable){

        Page<Client> clientPage;

        if (filtros.containsKey("name")) {
            String name = filtros.get("name");
            clientPage = findAllByName(name,pageable);

        } else if (filtros.containsKey("email")){
            String email = filtros.get("email");
            clientPage = findAllByEmail(email,pageable);
        }
        else if (filtros.containsKey("phome")) {
            String phome = filtros.get("phome");
            clientPage = findAllByPhone(phome,pageable);

        }
        else throw new BadRequestException("Field Incorrect","Ensure that the field name you entered in the request is correct");

        return clientPage;
    }
}
