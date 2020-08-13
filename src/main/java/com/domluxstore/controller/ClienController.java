package com.domluxstore.controller;

import com.domluxstore.converter.domain.cliente.ClienteGetDto;
import com.domluxstore.converter.domain.cliente.ClientePostDto;
import com.domluxstore.converter.mapperes.ClientMapper;
import com.domluxstore.domain.Client;
import com.domluxstore.domain.User;
import com.domluxstore.exception.ConflictException;
import com.domluxstore.service.map.ClientService;
import com.domluxstore.service.map.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Client", description = ": RestController of client", tags = {"Client"})
public class ClienController{

    private final UserService userService;
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @Autowired
    public ClienController(UserService userService, ClientService clientService, ClientMapper clientMapper) {
        this.userService = userService;
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @ApiOperation(value= "Save client")
    @PostMapping("/client")
    public ResponseEntity<ClienteGetDto> save(@Valid @RequestBody ClientePostDto clientePostDto){

        Map<String, String> erros = new HashMap<>();

        User user1 = userService.finByEmail(clientePostDto.getEmail());

        if (user1!=null){
            erros.put("email","The email  is already being used");
        }

        User user2 = userService.finByPhone(clientePostDto.getPhone());

        if (user2!=null){
            erros.put("phone","The phone number is already being used");
        }

        if (!erros.isEmpty()){

            throw new ConflictException("Information conflits",erros);
        }

        Client client = clientMapper.clientPostDtoToClient(clientePostDto);

        client = clientService.save(client);

        return new ResponseEntity<>(convertDto(client),HttpStatus.OK);
    }

    @ApiOperation(value= "List all adminitratores")
    @GetMapping("/clients")
    public ResponseEntity<Page<ClienteGetDto>> listAll(Pageable pageable){

        Page<Client> clientPage = clientService.listAll(pageable);

        List<ClienteGetDto> clienteGetDtos = convertDtos(clientPage.getContent());

        return new ResponseEntity<>(new PageImpl<>(clienteGetDtos,clientPage.getPageable(),clientPage.getTotalElements()),HttpStatus.OK);
    }

    @ApiOperation(value= "Search adminitrator")
    @GetMapping("/clients/search")
    public ResponseEntity<Page<ClienteGetDto>> search(@RequestParam Map<String,String> filter,Pageable pageable){

        Page<Client> clientPage = clientService.filterSerach(filter,pageable);

        List<ClienteGetDto> clienteGetDtos =convertDtos(clientPage.getContent());

        return new ResponseEntity<>(new PageImpl<>(clienteGetDtos,clientPage.getPageable(),clientPage.getTotalElements()),HttpStatus.OK);
    }

    private ClienteGetDto convertDto(Client client){
        return clientMapper.clientToClientGetDto(client);
    }

    private List<ClienteGetDto> convertDtos(List<Client> clients){
        return clients.stream().map(clientMapper::clientToClientGetDto).collect(Collectors.toList());
    }


}
