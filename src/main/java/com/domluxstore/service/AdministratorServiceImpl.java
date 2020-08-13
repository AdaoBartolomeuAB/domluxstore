package com.domluxstore.service;

import com.domluxstore.converter.domain.administrator.AdministratorPutDto;
import com.domluxstore.converter.domain.user.UserPutDto;
import com.domluxstore.converter.mapperes.UserMapper;
import com.domluxstore.domain.Administrator;
import com.domluxstore.domain.Client;
import com.domluxstore.domain.User;
import com.domluxstore.domain.enumeration.AdministratorType;
import com.domluxstore.exception.BadRequestException;
import com.domluxstore.repository.AdministratorRepository;
import com.domluxstore.service.map.AdministratorService;
import com.domluxstore.service.map.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository administratorRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository administratorRepository, UserService userService, UserMapper userMapper) {
        this.administratorRepository = administratorRepository;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public Administrator save(Administrator administrator){

        User user = userService.save(administrator.getUser(),"ROLE_ADMIN");

        administrator.setAdministratorType(AdministratorType.ADMIN);
        administrator.setUser(user);
        return administratorRepository.save(administrator);
    }

    @Override
    public Administrator update(String id,AdministratorPutDto administratorPutDto) {

        Administrator administrator = findById(id);

        UserPutDto user = userMapper.administradorPutDtoToUserPutDto(administratorPutDto);

        User user1 = userService.update(administrator.getUser().getId(),user);

        administrator.setUser(user1);
        return administratorRepository.save(administrator);
    }

    @Override
    public Page<Administrator> findAllByPhone(String phone,Pageable pageable) {
        return administratorRepository.findAllByUser_PhoneContaining(phone,pageable);
    }

    @Override
    public Page<Administrator> findAllByEmail(String email,Pageable pageable) {
        return administratorRepository.findAllByUser_EmailContaining(email,pageable);
    }

    @Override
    public Page<Administrator> findAllByName(String name,Pageable pageable) {
        return administratorRepository.findAllByUser_NameContaining(name,pageable);
    }

    @Override
    public Page<Administrator> listAll(Pageable pageable) {
        return administratorRepository.findAll(pageable);
    }

    @Override
    public Administrator findById(String id) {
        return administratorRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Administrator> filterSerach(Map<String,String> filtros, Pageable pageable){

        Page<Administrator> administrators;

        if (filtros.containsKey("name")) {
            String name = filtros.get("name");
            administrators = findAllByName(name,pageable);

        } else if (filtros.containsKey("email")){
            String email = filtros.get("email");
            administrators = findAllByEmail(email,pageable);
        }
        else if (filtros.containsKey("phome")) {
            String phome = filtros.get("phome");
            administrators = findAllByPhone(phome,pageable);

        }
        else throw new BadRequestException("Field Incorrect","Ensure that the field name you entered in the request is correct");

        return administrators;
    }
}
