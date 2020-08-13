package com.domluxstore.controller;

import com.domluxstore.converter.domain.administrator.AdministratorGetDto;
import com.domluxstore.converter.domain.administrator.AdministratorPostDto;
import com.domluxstore.converter.domain.administrator.AdministratorPutDto;
import com.domluxstore.converter.mapperes.AdministratorMapper;
import com.domluxstore.domain.Administrator;
import com.domluxstore.domain.Client;
import com.domluxstore.domain.User;
import com.domluxstore.exception.ConflictException;
import com.domluxstore.exception.RecourceNotFoundException;
import com.domluxstore.service.map.AdministratorService;
import com.domluxstore.service.map.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Administrator", description = ": RestController of administrator", tags = {"Administrator"})
public class AdministratorController {

    private final AdministratorService administratorService;
    private final AdministratorMapper administratorMapper;
    private final UserService userService;

    public AdministratorController(AdministratorService administratorService, AdministratorMapper administratorMapper, UserService userService) {
        this.administratorService = administratorService;
        this.administratorMapper = administratorMapper;
        this.userService = userService;
    }

    @ApiOperation(value= "Save adminitrator")
    @PostMapping("/administrator")
    public ResponseEntity<AdministratorGetDto> save(@Valid @RequestBody AdministratorPostDto administratorPostDto){

        Map<String, String> erros = new HashMap<>();

        User user1 = userService.finByEmail(administratorPostDto.getEmail());

        if (user1!=null){
            erros.put("email","The email is already being used");
        }

        User user2 = userService.finByPhone(administratorPostDto.getPhone());

        if (user2!=null){
            erros.put("phone","The phone number is already being used");
        }

        if (!erros.isEmpty()){

            throw new ConflictException("Information conflits",erros);
        }

        Administrator administrator = administratorMapper.administratorPostDtoToAdministrator(administratorPostDto);

        administrator = administratorService.save(administrator);

        return new ResponseEntity<>(convertDto(administrator), HttpStatus.OK);
    }


    @ApiOperation(value= "update adminitrator")
    @PutMapping("/administrator/{id}")
    public ResponseEntity<AdministratorGetDto> update(@PathVariable String id,@Valid @RequestBody AdministratorPutDto administratorPutDto){

        Administrator administrator = administratorService.findById(id);

        if (administrator==null) throw new RecourceNotFoundException("Administrator not found", "Ensure that the id is correct or the administrator that you want to find dont exist");

        Map<String, String> erros = new HashMap<>();

        User user = administrator.getUser();

        User user1 = userService.finByEmail(administratorPutDto.getEmail());

        if (user1!=null && !user.getId().equals(user1.getId())){
            erros.put("email","The email is already being used");
        }

        User user2 = userService.finByPhone(administratorPutDto.getPhone());

        if (user2!=null && !user.getId().equals(user2.getId())){
            erros.put("phone","The phone number is already being used");
        }

        if (!erros.isEmpty()){
            throw new ConflictException("Information conflits",erros);
        }

        Administrator administrator1 = administratorService.update(id,administratorPutDto);
        return new ResponseEntity<>(convertDto(administrator1), HttpStatus.OK);
    }

    @ApiOperation(value= "List all adminitratores")
    @GetMapping("/administratores")
    public ResponseEntity<Page<AdministratorGetDto>> listAll(Pageable pageable){

        Page<Administrator> administratorPage = administratorService.listAll(pageable);

        List<AdministratorGetDto> administratorGetDtos = convertDtos(administratorPage.getContent());

        return new ResponseEntity<>(new PageImpl<>(administratorGetDtos,administratorPage.getPageable(),administratorPage.getTotalElements()),HttpStatus.OK);
    }

    @ApiOperation(value= "Search adminitrator")
    @GetMapping("/administratores/search")
    public ResponseEntity<Page<AdministratorGetDto>> search(@RequestParam Map<String,String> filtros,Pageable pageable){

        Page<Administrator> administratorPage = administratorService.filterSerach(filtros,pageable);

        List<AdministratorGetDto> administratorGetDtos =convertDtos(administratorPage.getContent());

        return new ResponseEntity<>(new PageImpl<>(administratorGetDtos,administratorPage.getPageable(),administratorPage.getTotalElements()),HttpStatus.OK);

    }
    private AdministratorGetDto convertDto(Administrator administrator){
        return administratorMapper.administratorToAdministratorGetDto(administrator);
    }

    private List<AdministratorGetDto> convertDtos(List<Administrator> administratores){
        return administratores.stream().map(administratorMapper::administratorToAdministratorGetDto).collect(Collectors.toList());
    }
}
